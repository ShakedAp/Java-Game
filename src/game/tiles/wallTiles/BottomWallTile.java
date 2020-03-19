package game.tiles.wallTiles;

import game.gfx.Assets;
import game.tiles.Tile;

public class BottomWallTile extends Tile{

	public BottomWallTile(int id) {
		super(Assets.wall_tiles[6], id);
	}

	@Override
	public boolean isSolid(){
		return true;
	}
}
