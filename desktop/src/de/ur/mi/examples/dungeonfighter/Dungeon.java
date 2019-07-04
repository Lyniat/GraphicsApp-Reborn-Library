package de.ur.mi.examples.dungeonfighter;

import de.ur.mi.graphics.Compound;
import de.ur.mi.graphics.Image;
import de.ur.mi.util.RandomGenerator;

public class Dungeon extends Compound {

    private boolean[][] collisionMap;

    private Compound bottomLayer;

    private Compound topLayer;

    public Dungeon(){
        super(0,0);
        bottomLayer = new Compound();
        topLayer = new Compound();
        createMap();
    }

    public void drawBottomLayer(){
        bottomLayer.draw();
    }

    public void drawTopLayer(){
        topLayer.draw();
    }

    private void createMap(){
        collisionMap = new boolean[DungeonFighter.DUNGEON_SIZE][DungeonFighter.DUNGEON_SIZE];

        for(int x = 0; x < DungeonFighter.DUNGEON_SIZE; x++) {
            for (int y = 0; y < DungeonFighter.DUNGEON_SIZE; y++) {
                collisionMap[x][y] = false;
            }
        }

        Image i;

        for(int x = 0; x < DungeonFighter.DUNGEON_SIZE; x++){
            for(int y = 0; y < DungeonFighter.DUNGEON_SIZE; y++){
                int normalFloor = RandomGenerator.getInstance().nextInt(0,10);
                if(normalFloor <=7){
                    i = new Image(x*DungeonFighter.TILE_SIZE,y*DungeonFighter.TILE_SIZE,DungeonFighter.TILE_SIZE,DungeonFighter.TILE_SIZE,Resources.TILE_DUNGEON_FLOOR_1);
                }else{
                    int otherFloor = RandomGenerator.getInstance().nextInt(2,8);
                    String image;
                    switch (otherFloor) {
                        case 2:
                            image = Resources.TILE_DUNGEON_FLOOR_2;
                            break;
                        case 3:
                            image = Resources.TILE_DUNGEON_FLOOR_3;
                            break;
                        case 4:
                            image = Resources.TILE_DUNGEON_FLOOR_4;
                            break;
                        case 5:
                            image = Resources.TILE_DUNGEON_FLOOR_5;
                            break;
                        case 6:
                            image = Resources.TILE_DUNGEON_FLOOR_6;
                            break;
                        case 7:
                            image = Resources.TILE_DUNGEON_FLOOR_7;
                            break;
                        default:
                            image = Resources.TILE_DUNGEON_FLOOR_8;
                    }

                    i = new Image(x*DungeonFighter.TILE_SIZE,y*DungeonFighter.TILE_SIZE,DungeonFighter.TILE_SIZE,DungeonFighter.TILE_SIZE,image);
                }

                bottomLayer.add(i);
            }
        }

        for(int x = 0; x < DungeonFighter.DUNGEON_SIZE; x++){
            i = new Image(x*DungeonFighter.TILE_SIZE,0,DungeonFighter.TILE_SIZE,DungeonFighter.TILE_SIZE,Resources.TILE_DUNGEON_WALL);
            bottomLayer.add(i);

            collisionMap[x][0] = true;
        }

        for(int x = 0; x < DungeonFighter.DUNGEON_SIZE; x++){
            i = new Image(x*DungeonFighter.TILE_SIZE,(DungeonFighter.DUNGEON_SIZE-1) * DungeonFighter.TILE_SIZE,DungeonFighter.TILE_SIZE,DungeonFighter.TILE_SIZE,Resources.TILE_DUNGEON_WALL);
            bottomLayer.add(i);

            i = new Image(x*DungeonFighter.TILE_SIZE,(DungeonFighter.DUNGEON_SIZE-2) * DungeonFighter.TILE_SIZE,DungeonFighter.TILE_SIZE,DungeonFighter.TILE_SIZE,Resources.TILE_DUNGEON_WALL_TOP);
            topLayer.add(i);

            collisionMap[x][DungeonFighter.DUNGEON_SIZE-1] = true;
        }

        for(int y = 1; y < DungeonFighter.DUNGEON_SIZE-1; y++){
            i = new Image(0,y * DungeonFighter.TILE_SIZE,DungeonFighter.TILE_SIZE,DungeonFighter.TILE_SIZE,Resources.TILE_DUNGEON_WALL_LEFT);
            bottomLayer.add(i);

            collisionMap[0][y] = true;
        }

        for(int y = 1; y < DungeonFighter.DUNGEON_SIZE-1; y++){
            i = new Image((DungeonFighter.DUNGEON_SIZE-1)*DungeonFighter.TILE_SIZE,y * DungeonFighter.TILE_SIZE,DungeonFighter.TILE_SIZE,DungeonFighter.TILE_SIZE,Resources.TILE_DUNGEON_WALL_RIGHT);
            bottomLayer.add(i);

            collisionMap[(DungeonFighter.DUNGEON_SIZE-1)][y] = true;
        }

        for(int x = 2; x < DungeonFighter.DUNGEON_SIZE-2; x++) {
            for (int y = 2; y < DungeonFighter.DUNGEON_SIZE - 2; y++) {
                int createColumn = RandomGenerator.getInstance().nextInt(0, 20);
                if (createColumn > 19) {
                    i = new Image(x * DungeonFighter.TILE_SIZE, (y + 1) * DungeonFighter.TILE_SIZE, DungeonFighter.TILE_SIZE, DungeonFighter.TILE_SIZE, Resources.TILE_DUNGEON_COLUMN_BASE);
                    bottomLayer.add(i);

                    i = new Image(x * DungeonFighter.TILE_SIZE, y * DungeonFighter.TILE_SIZE, DungeonFighter.TILE_SIZE, DungeonFighter.TILE_SIZE, Resources.TILE_DUNGEON_COLUMN_MID);
                    bottomLayer.add(i);
                    collisionMap[x][y] = true;

                    i = new Image(x * DungeonFighter.TILE_SIZE, (y - 1) * DungeonFighter.TILE_SIZE, DungeonFighter.TILE_SIZE, DungeonFighter.TILE_SIZE, Resources.TILE_DUNGEON_COLUMN_TOP);
                    topLayer.add(i);
                }
            }
        }

    }

    public boolean[][] getCollisionMap() {
        return collisionMap;
    }

}
