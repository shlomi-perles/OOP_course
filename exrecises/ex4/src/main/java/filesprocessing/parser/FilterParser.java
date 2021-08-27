package filesprocessing.parser;

import filesprocessing.filter.Filter;

public class FilterParser {
    /**
     * the parse line
     */
    private String line;

    public FilterParser(String line)
    {
        this.line = line;
    }

    Filter parse() throws Exception { //TODO

    }

    public BetweenFilter(String string) throws FilterException{
        String[] parts = string.split(SEPARATOR);
        try{
            this.lowerBoundary = Double.parseDouble(parts[0]);
            this.upperBoundary = Double.parseDouble(parts[1]);
            if(this.lowerBoundary < 0 || this.upperBoundary < 0 || this.lowerBoundary > this.upperBoundary){
                throw new FilterException();
            }
        }
        catch (NumberFormatException e){
            throw new FilterException();
        }
    }
}
