package com.example.and14_allview.listview;

public class AheDTO {
    //R.drawable.image이름 == R.int형으로 저장되어 있음
    private String name, species;
    private int age, img_id;

    public AheDTO(String name, String species, int age, int img_id) {
        this.name = name;
        this.species = species;
        this.age = age;
        this.img_id = img_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getImg_id() {
        return img_id;
    }

    public void setImg_id(int img_id) {
        this.img_id = img_id;
    }
}
