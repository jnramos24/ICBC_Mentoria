package org.example.model;

public class ImageDocument implements Document {

    public ImageDocument() {
    }

    @Override
    public void open() {
        System.out.println("Opening image document...");
    }

    @Override
    public void save() {
        System.out.println("Saving image document...");
    }

    @Override
    public void close() {
        System.out.println("Closing image document...");
    }
}
