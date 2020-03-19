package game.tiles.wallTiles;

import game.gfx.Assets;
import game.tiles.Tile;

public class LeftWallTile extends Tile{

	public LeftWallTile(int id) {
		super(Assets.wall_tiles[7], id);
	}

	@Override
	public boolean isSolid(){
		return true;
	}
}
