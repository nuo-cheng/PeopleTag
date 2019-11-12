package com.example.getstarted.objects;

/**
 * Assoc class
 * Association of person and collection
 */
public class Assoc {
    public static final String ID="id";
    public static final String PERSON_ID="personId";
    public static final String COLLECTION_ID="collectionId";
    private Long id;
    private Long personId;
    private Long collectionId;

    /**
     * constructor
     * @param id
     * @param personId
     * @param collectionId
     */
    public Assoc(Long id, Long personId, Long collectionId){
        this.id=id;
        this.personId=personId;
        this.collectionId=collectionId;
    }

    /**
     * set Assoc id
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * set personId
     * @param personId
     */
    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    /**
     * set collectionId
     * @param collectionId
     */
    public void setCollectionId(Long collectionId) {
        this.collectionId = collectionId;
    }

    /**
     * get personId
     * @return
     */
    public Long getPersonId() {
        return personId;
    }

    /**
     * get collectionId
     * @return
     */
    public Long getCollectionId() {
        return collectionId;
    }

    /**
     * get Assoc id
     * @return
     */
    public Long getId() {
        return id;
    }
}
