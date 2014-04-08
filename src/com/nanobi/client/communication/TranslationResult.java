package com.nanobi.client.communication;


import java.util.HashMap;
import java.util.Map;



/**
 * @author samarth
 * 
 */
public class TranslationResult {
    private String query;

    private Map<String, String> measures;
    private Map<String, String> dimensions;
    private Map<String, String> filters;
    private Map<String, String> visualizations;

    public TranslationResult() {
        measures = new HashMap<String, String>();
        dimensions = new HashMap<String, String>();
        filters = new HashMap<String, String>();
        visualizations = new HashMap<String, String>();
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Map<String, String> getMeasures() {
        return measures;
    }

    public Map<String, String> getDimensions() {
        return dimensions;
    }

    public Map<String, String> getFilters() {
        return filters;
    }

    public Map<String, String> getVisualizations() {
        return visualizations;
    }

    public void addMeasure(String token, String str) {
        measures.put(token, str);
    }

    public void addDimension(String token, String str) {
        dimensions.put(token, str);
    }

    public void addFilter(String token, String str) {
        filters.put(token, str);
    }

    public void addVisualization(String token, String str) {
        visualizations.put(token, str);
    }

    @Override
    public String toString() {
        return "TranslationResult [query=" + query + ", measures=" + measures
                + ", dimensions=" + dimensions + ", filters=" + filters
                + ", visualizations=" + visualizations + "]";
    }

}
