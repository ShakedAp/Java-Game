package game.tiles.wallTiles;

import game.gfx.Assets;
import game.tiles.Tile;

public class TopRightWallTile extends Tile{

	public TopRightWallTile(int id) {
		super(Assets.wall_tiles[1], id);
	}

	@Override
	public boolean isSolid(){
		return true;
	}
}
