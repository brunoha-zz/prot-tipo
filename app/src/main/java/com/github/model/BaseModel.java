package com.github.model;

import java.util.List;

public class BaseModel {

    private List<TypeModel> types;

    private List<AuthorModel> authors;

    public BaseModel(List<TypeModel> types, List<AuthorModel> authors) {
        this.types = types;
        this.authors = authors;
    }

    public List<TypeModel> getTypes() {
        return types;
    }

    public void setTypes(List<TypeModel> types) {
        this.types = types;
    }

    public List<AuthorModel> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorModel> authors) {
        this.authors = authors;
    }
}
