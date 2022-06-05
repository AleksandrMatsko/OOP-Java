package Chat;

import Exceptions.InvalidUserNameException;
import Names.UserName;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Message {
    private Document parsedXML;
    private StringBuilder stringXML;

    private static ConcurrentHashMap<MessageType, String> typeNames = null;

    public Message(String messageData, MessageType messageType, UserName userName) {
        initTypeNames();

        try {
            stringXML = new StringBuilder();
            XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
            XMLStreamWriter writer = outputFactory.createXMLStreamWriter(new OutputStreamWriter(new OutputStream() {
                @Override
                public void write(int b) {
                    stringXML.append((char) b);
                }
            }));

            writer.writeStartDocument("1.0");
            writer.writeStartElement("message");

            writer.writeStartElement("type");
            writer.writeCharacters(typeNames.get(messageType));
            writer.writeEndElement();

            Calendar date = new GregorianCalendar();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            writer.writeStartElement("date");
            writer.writeCharacters(formatter.format(date.getTime()));
            writer.writeEndElement();

            writer.writeStartElement("userName");
            if (userName != null) {
                writer.writeCharacters(userName.getName());
            }
            else {
                writer.writeCharacters("");
            }
            writer.writeEndElement();

            writer.writeStartElement("text");
            writer.writeCharacters(messageData);
            writer.writeEndElement();

            writer.writeEndElement();
            writer.writeEndDocument();
            writer.flush();

            parsedXML = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                    .parse(new InputSource(new StringReader(stringXML.toString())));
        }
        catch (XMLStreamException | ParserConfigurationException | SAXException | IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void initTypeNames() {
        if (typeNames == null) {
            typeNames = new ConcurrentHashMap<MessageType, String>();
            for (MessageType type : MessageType.values()) {
                typeNames.put(type, type.name());
            }
        }
    }

    public Message() {
        initTypeNames();
        parsedXML = null;
    }

    public void unpackMessage(byte[] packedMessage) {
        try {
            parsedXML = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(packedMessage));
        }
        catch (IOException | ParserConfigurationException | SAXException ex) {
            ex.printStackTrace();
        }
        createStringXML();
    }

    public Document getParsedXML() {
        return parsedXML;
    }

    public byte[] packMessage() {
        return stringXML.toString().getBytes(StandardCharsets.UTF_8);
    }

    private void createStringXML() {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = transformerFactory.newTransformer();
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        }
        DOMSource source = new DOMSource(parsedXML);
        StreamResult result = new StreamResult(new StringWriter());
        try {
            transformer.transform(source, result);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }

        stringXML = new StringBuilder(result.getWriter().toString());

    }

    public MessageType getMessageType() {
        return MessageType.valueOf(parsedXML.getElementsByTagName("type").item(0).getTextContent());
    }

    public void setMessageType(MessageType messageType) {
        parsedXML.getElementsByTagName("type").item(0).setTextContent(messageType.name());
    }

    public String getMessageData() {
        return parsedXML.getElementsByTagName("text").item(0).getTextContent();
    }

    public String getDate() {
        return parsedXML.getElementsByTagName("date").item(0).getTextContent();
    }


    public UserName getSenderName() {
        UserName userName = null;
        try {
            userName = new UserName(parsedXML.getElementsByTagName("userName").item(0).getTextContent());
        }
        catch (InvalidUserNameException ex) {
            throw new RuntimeException(ex);
        }
        return userName;
    }


}
