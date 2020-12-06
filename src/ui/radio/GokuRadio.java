package ui.radio;

import java.awt.Graphics;

import app.controller.GameController;
import core.asset.Asset;
import java.awt.image.BufferedImage;

public class GokuRadio extends Radio
{   
    private BufferedImage innerImage;

    private BufferedImage characterImage;

    public GokuRadio(GameController game, float positionX, float positionY, int xx, int yy)
    {
        super(game, positionX, positionY, xx, yy);
    }

    @Override
    protected void loadSize()
    {
        width = 100;
        height = 100;
    }

    @Override
    protected void loadUIImage()
    {
        images.add(Asset.ui03.crop(6, 50, 38, 38));
        images.add(Asset.ui03.crop(190, 50, 38, 38));
        innerImage = Asset.gokuAvatar.crop(0, 0, 121, 145);
        characterImage = Asset.gokuBackground.crop(0, 0, 1920, 1080);

        currentImage = images.get(0);
    }

    @Override
    public void onWaiting()
    {
        currentImage = images.get(0);
    }

    @Override
    public void onHover()
    {
        currentImage = images.get(1);
    }

    @Override
    public void onClick()
    {
        super.onClick();

        currentImage = images.get(1);
        sharedElement.setCurrentImage(characterImage);
    }

    @Override
    public void render(Graphics graphics)
    {
        super.render(graphics);

        graphics.drawImage(innerImage, x + 7, y + 7 , 86, 86, null);
    }
}