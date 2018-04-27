package br.com.naka.dogs.bean.response;

import java.util.List;

public class FeedResponse {

    private String category;
    private List<String> list;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
