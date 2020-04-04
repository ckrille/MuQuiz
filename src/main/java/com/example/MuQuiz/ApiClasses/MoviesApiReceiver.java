package com.example.MuQuiz.ApiClasses;

import java.util.List;

public class MoviesApiReceiver {

    public String page;
    public String total_results;
    public String total_pages;
    public List<Movies> results;

    public MoviesApiReceiver() {
    }

    public MoviesApiReceiver(String page, String total_results, String total_pages, List<Movies> results) {
        this.page = page;
        this.total_results = total_results;
        this.total_pages = total_pages;
        this.results = results;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getTotal_results() {
        return total_results;
    }

    public void setTotal_results(String total_results) {
        this.total_results = total_results;
    }

    public String getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(String total_pages) {
        this.total_pages = total_pages;
    }

    public List<Movies> getResults() {
        return results;
    }

    public void setResults(List<Movies> results) {
        this.results = results;
    }
}
