package com.group18.asdc.groupformation;

import java.util.ArrayList;

public class GroupFormation implements IGroupFormation {

    private Float[][][] distanceMatrix = null;

    public GroupFormation(Float[][][] distanceMatrix) {
        this.distanceMatrix = distanceMatrix;
    }

    @Override
    public ArrayList<ArrayList> formGroups() {
        if (distanceMatrix.length == 0) {
            return null;
        } else {

        }
        return null;
    }

}