package Model.ScoreTable;

import Model.Names.UserName;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class HighScoreTable {
    private final TreeSet<PairKeyVal<UserName, Integer>> scoreTable;
    private final int maxSize;
    private final String regex;

    private final Comparator<PairKeyVal<UserName, Integer>> cmp = new Comparator<PairKeyVal<UserName, Integer>>() {
        @Override
        public int compare(PairKeyVal<UserName, Integer> o1, PairKeyVal<UserName, Integer> o2) {
            int comparison = o2.getValue().compareTo(o1.getValue());
            if (comparison == 0) {
                return o1.getKey().compareTo(o2.getKey());
            }
            return comparison;
        }
    };

    public HighScoreTable(int maxSize) {
        scoreTable = new TreeSet<>(cmp);
        regex = "=";
        ScoreFileParser parser = null;
        try {
            parser = new ScoreFileParser(regex);
            while (parser.hasNextRecord()) {
                scoreTable.add(parser.getPair());
            }
        }
        finally {
            if (parser != null) {
                parser.close();
            }
        }
        this.maxSize = maxSize;
    }

    public void add(UserName userName, int score) {
        scoreTable.add(new PairKeyVal<>(userName, score));
        if (scoreTable.size() > maxSize) {
            scoreTable.remove(scoreTable.last());
        }
        ScoreFileWriter writer = null;
        try {
            writer = new ScoreFileWriter(regex);
            for (PairKeyVal<UserName, Integer> pairKeyVal : scoreTable) {
                writer.writeScore(pairKeyVal);
            }
        }
        finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    public List<PairKeyVal<UserName, Integer>> getList() {
        return new ArrayList<PairKeyVal<UserName, Integer>>(scoreTable);
    }

    public String getTableAsString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (PairKeyVal<UserName, Integer> pair : scoreTable) {
            stringBuilder.append(pair.getKey().getName());
            stringBuilder.append(" ");
            stringBuilder.append(pair.getValue());
            stringBuilder.append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }

}
