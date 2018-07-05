import db.DBConsole;
import db.DBGame;
import db.DBHelper;
import models.Console;
import models.Game;
import models.GenreType;
import models.Owner;

import java.util.List;

public class Runner {

    public static void main(String[] args) {
        //SAVE
        Console console1 = new Console("Sony", "PS4", "Europe");
        Console console2 = new Console("Microsoft", "XBOX", "USA");
        Game game1 = new Game("COD", GenreType.FPS);
        Game game2 = new Game("Sims", GenreType.ARCADE);
        Game game3 = new Game("Sims2", GenreType.ARCADE);

        Owner owner1 = new Owner("Joe", game1);
        Owner owner2 = new Owner("John", game1);

        DBHelper.save(console1);
        DBHelper.save(console2);
        DBHelper.save(game1);
        DBHelper.save(game2);
        DBHelper.save(game3);
        DBHelper.save(owner1);
        DBHelper.save(owner2);

        //UPDATE
        game2.setGenre(GenreType.RPG);
        DBHelper.update(game2);

        //DELETE
        DBHelper.delete(game3);

        //ADD GAMES TO CONSOLES
        DBConsole.addGameToConsole(console1, game1);
        DBConsole.addGameToConsole(console1, game2);
        DBConsole.addGameToConsole(console2, game1);
        DBConsole.addGameToConsole(console2, game2);

        //LIST GAMES BY CONSOLE / CONSOLES BY GAME
        List<Game> console1Games = DBConsole.availableGames(console1);
        List<Console> game1Consoles = DBGame.availableConsoles(game1);

        List<Owner> ownersWhoseFavGameIs1 = DBGame.getOwnersWhoseFavGameIs(game1);

    }

}
