package ua.edu.ucu.apps;

public class Main {
    public static void main(String[] args) {
        Document document =new SmartDocument();
        document =new CachedDocument(new TimedDocument(document));
        document.parse("src/main/resources/helloWorld.png");
    }
}