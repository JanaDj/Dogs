package com.example.meangirl.dogs;

import java.util.ArrayList;
import java.util.List;

public class FCIData {

    private static FCIData instance;
    private List<String> groupsData;
    private List<String> group1;
    private List<String> group2;
    private List<String> group3;
    private List<String> group4;
    private List<String> group5;
    private List<String> group6;
    private List<String> group7;
    private List<String> group8;
    private List<String> group9;
    private List<String> group10;

    //getters
    public List<String> getGroup1() {
        return group1;
    }
    public List<String> getGroup2() {
        return group2;
    }
    public List<String> getGroup3() {
        return group3;
    }
    public List<String> getGroup4() {
        return group4;
    }
    public List<String> getGroup5() {
        return group5;
    }
    public List<String> getGroup6() {
        return group6;
    }
    public List<String> getGroup7() {
        return group7;
    }
    public List<String> getGroup8() {
        return group8;
    }
    public List<String> getGroup9() {
        return group9;
    }
    public List<String> getGroup10() {
        return group10;
    }
    public List<String> getGroupsData() {
        return groupsData;
    }
    /**
     * Private constructor
     */
    private FCIData() {
        addData();
    }
    /**
     * Method that returns instance of the instance (singleton implementation)
     * @return instance, FCIData
     */
    public static FCIData getInstance() {
        if(instance == null){
            instance = new FCIData();
        }
        return instance;
    }
    private void addData() {
        groupsData = new ArrayList<String>();
        group1 = new ArrayList<String>();
        group2 = new ArrayList<String>();
        group3 = new ArrayList<String>();
        group4 = new ArrayList<String>();
        group5 = new ArrayList<String>();
        group6 = new ArrayList<String>();
        group7 = new ArrayList<String>();
        group8 = new ArrayList<String>();
        group9 = new ArrayList<String>();
        group10 = new ArrayList<String>();
        //groups
        groupsData.add("Sheepdogs and Cattledogs (except Swiss Cattledogs)");
        groupsData.add("Pinscher and Schnauzer - Molossoid and Swiss Mountain and Cattledogs");
        groupsData.add("Terriers");
        groupsData.add("Dachshunds");
        groupsData.add("Spitz and primitive types");
        groupsData.add("Scent hounds and related breeds");
        groupsData.add("Pointing Dogs");
        groupsData.add("Retrievers - Flushing Dogs - Water Dogs");
        groupsData.add("Companion and Toy Dogs");
        groupsData.add("Sighthounds");
        //sections
        //group1
        group1.add("Sheepdogs");
        group1.add("Cattledogs (except Swiss Cattledogs)");
        //group2
        group2.add("Pinscher and Schnauzer type");
        group2.add("Molossian type");
        group2.add("Swiss Mountain and Cattledogs");
        //group3
        group3.add("Large and medium sized Terriers");
        group3.add("Small sized Terriers");
        group3.add("Bull type Terriers");
        group3.add("Toy Terriers");
        //group4
        group4.add("Dachshunds");
        //group5
        group5.add("Nordic Sledge Dogs");
        group5.add("Nordic Hunting Dogs");
        group5.add("Nordic Watchdogs and Herders");
        group5.add("European Spitz");
        group5.add("Asian Spitz and related breeds");
        group5.add("Primitive type");
        group5.add("Primitive type - Hunting dogs");
        //group6
        group6.add("Scent hounds");
        group6.add("Leash (scent) Hounds");
        group6.add("Related breeds");
        //group 7
        group7.add("Continental Pointing Dogs");
        group7.add("British and Irish Pinters and Setters");
        //group 8
        group8.add("Retrievers");
        group8.add("Flushing Dogs");
        group8.add("Water Dogs");
        //group9
        group9.add("Bichons and related breeds");
        group9.add("Poodle");
        group9.add("Small Belgian Dogs");
        group9.add("Hairless Dogs");
        group9.add("Tibetan breeds");
        group9.add("Chihuahueno");
        group9.add("English Toy Spaniels");
        group9.add("Japan Chin and Pekingese");
        group9.add("Continental Toy Spaniel and Russian Toy");
        group9.add("Kromfohrländer");
        group9.add("Small Molossian type Dogs");
        //group 10
        group10.add("Long-haired or fringed Sighthounds");
        group10.add("Rough-haired Sighthounds");
        group10.add("Short-haired Sighthounds");
    }
}
