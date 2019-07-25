import NET.BinanceApiConnection;
import utils.ListCutter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < 133; i ++) {
            list.add(i);
        }

        List<List> pieces = ListCutter.cut(list, 200);

        for(List piece : pieces) {
            System.out.println(piece.size());
        }
    }
}

