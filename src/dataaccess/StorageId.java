package dataaccess;

import business.Author;
import business.LibraryMember;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class StorageId {
    private static final StorageId INSTANCE  = new StorageId();

    private DataAccessFacade da;

    private StorageId(){
        this.da = new DataAccessFacade();
    }

    private Integer getMemberId(){
        HashMap<Integer, LibraryMember> m = da.readMemberMap();
        if(m == null){
            return 101;
        }
        ArrayList<Integer> arr = new ArrayList<>(m.keySet());
        Collections.sort(arr);
        return arr.getLast() + 1;
    }

    private int getAuthorId(){
        HashMap<Integer, Author> m = da.readAuthorsMap();
        if(m == null){
            return 101;
        }
        ArrayList<Integer> arr = new ArrayList<>(m.keySet());
        Collections.sort(arr);
        return arr.getLast() + 1;
    }

    public static int getMemberID(){
            return INSTANCE.getMemberId();
    }

    public static int getAuthorID(){
        return INSTANCE.getAuthorId();
    }
}
