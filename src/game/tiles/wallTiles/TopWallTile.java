package game.tiles.wallTiles;

import game.gfx.Assets;
import game.tiles.Tile;

public class TopWallTile extends Tile{

	public TopWallTile(int id) {
		super(Assets.wall_tiles[4], id);
	}

	@Override
	public boolean isSolid(){
		return true;
	}
}
