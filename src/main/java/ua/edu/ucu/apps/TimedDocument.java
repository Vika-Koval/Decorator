package ua.edu.ucu.apps;

public class TimedDocument extends DocumentDecorator {
    public TimedDocument(Document document) {
        super(document);
    }
    @Override
    public String parse(String path){
        long startTime = System.nanoTime();
        String result = super.parse(path);
        long endTime=System.nanoTime();
        long durationInNano=endTime -startTime;
        double durationInSeconds = durationInNano / 1_000_000.0;
        System.out.println("Time taken in seconds: "+ durationInSeconds);
        return result;
    }
}
