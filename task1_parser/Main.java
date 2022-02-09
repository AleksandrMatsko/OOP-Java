public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.exit(1);
        }
        WordCounter parser = new WordCounter();
        for (int i = 0; i < args.length - 1; i++) {
            parser.collectStatistics(args[i]);
        }
        parser.releaseStatistics(args[args.length - 1], ',');
    }
}
