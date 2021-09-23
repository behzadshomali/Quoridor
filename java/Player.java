import java.util.*;

public class Player {

    private int player_x;
    private int player_y;
    private String player_name;
    private int player_wall = 10;

    public Player(int x, int y, String name){
        this.player_x = x;
        this.player_y = y;
        this.player_name = name;
    }

    public int getPlayer_x() {
        return player_x;
    }

    public int getPlayer_y() {
        return player_y;
    }

    public String getPlayer_name() {
        return player_name;
    }

    public void setPlayer_x(int player_x) {
        this.player_x = player_x;
    }

    public void setPlayer_y(int player_y) {
        this.player_y = player_y;
    }

    public String[][] move(String[][] map, String command){
        String[] split_cmd = command.split("#");
        map[this.player_y][this.player_x] = "free";
        setPlayer_x(Integer.parseInt(split_cmd[1]));
        setPlayer_y(Integer.parseInt(split_cmd[2]));
        return map;
    }

    public String[][] put_wall(String[][] map, String command){
        String[] split_cmd = command.split("#");
        String x = split_cmd[1];
        String y = split_cmd[2];
        String orientation = split_cmd[3];

        this.player_wall -=1 ;

        if (orientation.equals("horizontal")) {
            map[Integer.parseInt(y)][Integer.parseInt(x)] = "block,"+x+"horizontal";
            map[Integer.parseInt(y)][Integer.parseInt(x) + 1] = "block,"+x+"horizontal"+y;

            map[Integer.parseInt(y)+ 1][Integer.parseInt(x)] = "block,"+x+"horizontal";
            map[Integer.parseInt(y) + 1][Integer.parseInt(x) + 1] = "block,"+x+"horizontal"+y;
        }
        else if (orientation.equals("vertical")) {
            map[Integer.parseInt(y)][Integer.parseInt(x)] = "block,"+x+"vertical"+y;
            map[Integer.parseInt(y) + 1][Integer.parseInt(x)] = "block,"+x+"vertical";

            map[Integer.parseInt(y)][Integer.parseInt(x) + 1] = "block,"+x+"vertical"+y;
            map[Integer.parseInt(y) + 1][Integer.parseInt(x) + 1] = "block,"+x+"vertical";
        }

        return map;
    }

    public String[] get_legal_move(String[][] map){
        int pX = this.player_x;
        int pY = this.player_y;
        System.out.println(pX + " , " + pY);
        Set<String> legal = new HashSet<String>();
        if ((pX+1) <= 8) {
            if ((map[pY][pX + 1].equals("free")) || (map[pY][pX].equals("free")) && map[pY][pX + 1].contains("block")){
                legal.add("move#"+(pX+1) +"#"+pY);
            }
        }
        if((pX + 2) <= 8){
            if (!map[pY][pX + 1].equals("free") && !map[pY][pX + 1].contains("block")) {
                legal.add("move#"+(pX+2) +"#"+pY);
            }
        }
        if ((pX - 1) >= 0){
            if ((map[pY][pX - 1].equals("free")) || (map[pY][pX].equals("free")) && map[pY][pX - 1].contains("block")) {
                legal.add("move#"+(pX-1) +"#"+pY);
            }
        }
        if ((pX - 2) >= 0){
            if (!map[pY][pX - 1].equals("free") && !map[pY][pX - 1].contains("block")) {
                legal.add("move#"+(pX-2) +"#"+pY);
            }
        }
        if ((pY + 1) <= 8){
            if ((map[pY + 1][pX].equals("free")) || (map[pY][pX].equals("free")) && map[pY + 1][pX].contains("block")) {
                legal.add("move#"+pX +"#"+(pY+1));
            }

        }
        if ((pY + 2) <= 8){
            if (!map[pY + 1][pX].equals("free") && !map[pY + 1][pX].contains("block")) {
                legal.add("move#"+pX +"#"+(pY+2));
            }
        }
        if ((pY - 1) >= 0){
            if ((map[pY - 1][pX].equals("free")) || (map[pY][pX].equals("free")) && map[pY - 1][pX].contains("block")) {
                legal.add("move#"+pX +"#"+(pY-1));
            }
        }

        if ((pY - 2) >= 0) {
            if (!map[pY - 1][pX].equals("free") && !map[pY - 1][pX].contains("block")){
                legal.add("move#"+pX +"#"+(pY-2));
            }

        }

        if (map[pY][pX].contains("block") && !map[pY][pX+1].equals(map[pY][pX]) && ((pX + 1) <= 8)) {
            legal.add("move#"+(pX+1) +"#"+(pY));
        }

        if (map[pY][pX].contains("block") && !map[pY][pX-1].equals(map[pY][pX]) && ((pX - 1) >= 0)) {
            legal.add("move#"+(pX-1) +"#"+(pY));
        }

        if (map[pY][pX].contains("block") && !map[pY+1][pX].equals(map[pY][pX]) && ((pY + 1) <= 8)) {
            legal.add("move#"+(pX) +"#"+(pY+1));
        }

        if (map[pY][pX].contains("block") && !map[pY-1][pX].equals(map[pY][pX]) && ((pX - 1) >= 0)) {
            legal.add("move#"+(pX) +"#"+(pY-1));

        }


        int index = 0;
        String[] temp = new String[legal.size()];
        for (String l: legal) {
            temp[index++] = l;
        }
        return temp;
    }

}
