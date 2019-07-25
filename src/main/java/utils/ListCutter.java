package utils;

import java.util.ArrayList;
import java.util.List;

public class ListCutter {
    public static List<List> cut(List list, int pieceSize) {
        List<List> out = new ArrayList<>();
        List copyList = new ArrayList(list);

        if(list.size() < pieceSize) {
            out.add(list);
            return out;
        } else {
            while(list.size() > pieceSize) {
                List subList = list.subList(0, pieceSize);
                copyList.removeAll(subList);
                out.add(new ArrayList(subList));
                list = new ArrayList(copyList);
            }
            if(list.size() > 0) {
                out.add(list);
            }
        }

        return out;
    }
}
