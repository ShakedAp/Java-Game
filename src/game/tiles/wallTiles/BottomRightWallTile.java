package game.tiles.wallTiles;

import game.gfx.Assets;
import game.tiles.Tile;

public class BottomRightWallTile extends Tile{

	public BottomRightWallTile(int id) {
		super(Assets.wall_tiles[2], id);
	}

	@Override
	public boolean isSolid(){
		return true;
	}
}
