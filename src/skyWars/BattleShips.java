package skyWars;

import java.util.ArrayList;

public class BattleShips {

    private Sky theSky = new Sky();

    public void play() {
        int row, square;

        // setting up the ships

        MasterSpaceship m1 = new MasterSpaceship();
        BattleShooter b1 = new BattleShooter();
        BattleCruiser b2 = new BattleCruiser();
        BattleShooter b3 = new BattleShooter();
        BattleStar b4 = new BattleStar();
        BattleShooter b0 = new BattleShooter();// you can add values
        row = 4;
        square = 5;

//        this.theSky.moveSpaceship(b0, row, square);
//        this.theSky.moveSpaceship(b1, 4, 2);
        this.theSky.moveSpaceship(b2, 1, 1);
        this.theSky.moveSpaceship(b3, 0, 1);
//        this.theSky.moveSpaceship(b4, 0, 1);
        this.theSky.moveSpaceship(m1, 0, 1);
        
        this.theSky.isConflict();
//        System.out.println(this.theSky.isThereAShipOn(1, 1));

        // check if there is a ship on a given square
//        System.out.println(this.theSky.isThereAShipOn(4, 4));

        // remove a ship from the grid
//        this.theSky.removeShip(b4, 4, 4);

        // get all the ships on a given square
//        ArrayList<Spaceship> ships = this.theSky.getCurrentSpaceships(row, square);
//        for (Spaceship ship : ships) {
//            System.out.println(ship.getSpaceshipName());
//        }

        // check if there is a conflict on the grid
//        boolean conflict = this.theSky.isConflict(4, 4, 3, 5);
//        System.out.println(conflict);
    }

}
