package game.tiles.wallTiles;

import game.gfx.Assets;
import game.tiles.Tile;

public class RightWallTile extends Tile{

	public RightWallTile(int id) {
		super(Assets.wall_tiles[5], id);
	}

	@Override
	public boolean isSolid(){
		return true;
	}
}
