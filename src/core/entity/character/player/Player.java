package core.entity.character.player;

import core.entity.character.Character;
import helper.Helper;
import app.controller.GameController;
import app.model.MonsterModel;

public abstract class Player extends Character
{
    public Player(GameController game, float x, float y, int width, int height, int health, int damage, float speed)
    {
        super(game , x, y, width, height, health, damage, speed);
    }

    private void detectAttack()
    {
        MonsterModel.all().forEach(monster -> {
            boolean upperLeftCornerCollied = Helper.inSquare(x + padding, y + padding,
                    monster.getX(), monster.getY(), monster.getWidth(), monster.getHeight());
            boolean lowerLeftCornerCollied = Helper.inSquare(x + padding, y + height - padding,
                    monster.getX(), monster.getY(), monster.getWidth(), monster.getHeight());
            boolean upperRightCornerCollied = Helper.inSquare(x + width - padding, y + padding,
                    monster.getX(), monster.getY(), monster.getWidth(), monster.getHeight());
            boolean lowerRightCornerCollied = Helper.inSquare(x + width - padding, y + height - padding,
                    monster.getX(), monster.getY(), monster.getWidth(), monster.getHeight());

            if (upperLeftCornerCollied
                    || lowerLeftCornerCollied
                    || upperRightCornerCollied
                    || lowerRightCornerCollied
            ) {
                long now = System.currentTimeMillis();

                if (now - attackedAt >= 1000) {
                    health -= monster.getDamage();
                    attackedAt = now;
                }
            }
        });
    }
    
    public void tick()
    {
        super.tick();

        detectAttack();

        if (game.getKeyService().up.isPressed()) {
            moveUp(speed);
        }

        if (game.getKeyService().down.isPressed()) {
            moveDown(speed);
        }

        if (game.getKeyService().left.isPressed()) {
            moveLeft(speed);
        }
        
        if (game.getKeyService().right.isPressed()) {
            moveRight(speed);
        }
    }
}
