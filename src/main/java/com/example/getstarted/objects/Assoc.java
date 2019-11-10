package com.example.getstarted.objects;

public class Assoc {
    public static final String ID="id";
    public static final String PERSON_ID="personId";
    public static final String COLLECTION_ID="collectionId";
    private Long id;
    private Long personId;
    private Long collectionId;
    public Assoc(Long id, Long personId, Long collectionId){
        this.id=id;
        this.personId=personId;
        this.collectionId=collectionId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public void setCollectionId(Long collectionId) {
        this.collectionId = collectionId;
    }

    public Long getPersonId() {
        return personId;
    }

    public Long getCollectionId() {
        return collectionId;
    }

    public Long getId() {
        return id;
    }
}
