package org.lab1.data.entity;

public interface Ownerable {
    void setOwner(User owner);
    User getOwner();
}
