package game.tiles.wallTiles;

import game.gfx.Assets;
import game.tiles.Tile;

public class BottomLeftWallTile extends Tile{

	public BottomLeftWallTile(int id) {
		super(Assets.wall_tiles[3], id);
	}

	@Override
	public boolean isSolid(){
		return true;
	}
}
