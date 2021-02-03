package com.molean.railteleport;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Rail;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RailUtils {

    public static Rail.Shape getRailShape(Block block) {
        if (block.getType().name().toLowerCase().contains("rail")) {
            Rail railData = (Rail) block.getBlockData();
            return railData.getShape();
        }
        return null;
    }

    public static Block getFollowingRail(Direction direction, Block firstRail) {
        List<Block> connectedRails = RailUtils.getConnectedRails(firstRail);
        Block following = null;
        switch (direction) {
            case NORTH: {
                for (Block connectedRail : connectedRails) {
                    if (connectedRail.getZ() < firstRail.getZ()) {
                        following = connectedRail;
                        break;
                    }
                }
                break;
            }
            case EAST: {
                for (Block connectedRail : connectedRails) {
                    if (connectedRail.getX() > firstRail.getX()) {
                        following = connectedRail;
                        break;
                    }
                }
                break;
            }
            case SOUTH: {
                for (Block connectedRail : connectedRails) {
                    if (connectedRail.getZ() > firstRail.getZ()) {
                        following = connectedRail;
                        break;
                    }
                }
                break;
            }
            case WEST: {
                for (Block connectedRail : connectedRails) {
                    if (connectedRail.getX() < firstRail.getX()) {
                        following = connectedRail;
                        break;
                    }
                }
                break;
            }
        }
        return following;
    }

    public static List<Block> getConnectedRails(Block outerBlock) {
        Rail.Shape outerShape = getRailShape(outerBlock);
        List<Block> blocks = new ArrayList<>();
        switch (Objects.requireNonNull(outerShape)) {
            case NORTH_SOUTH: {
                {
                    Block block = outerBlock.getRelative(BlockFace.NORTH);
                    Rail.Shape shape = getRailShape(block);
                    if (shape == Rail.Shape.SOUTH_EAST ||
                            shape == Rail.Shape.SOUTH_WEST ||
                            shape == Rail.Shape.NORTH_SOUTH ||
                            shape == Rail.Shape.ASCENDING_NORTH
                    ) blocks.add(block);
                }
                {
                    Block block = outerBlock.getRelative(BlockFace.DOWN).getRelative(BlockFace.NORTH);
                    Rail.Shape shape = getRailShape(block);
                    if (shape == Rail.Shape.ASCENDING_SOUTH)
                        blocks.add(block);
                }


                {
                    Block block = outerBlock.getRelative(BlockFace.SOUTH);
                    Rail.Shape shape = getRailShape(block);
                    if (shape == Rail.Shape.NORTH_EAST ||
                            shape == Rail.Shape.NORTH_WEST ||
                            shape == Rail.Shape.NORTH_SOUTH ||
                            shape == Rail.Shape.ASCENDING_SOUTH
                    ) blocks.add(block);
                }
                {
                    Block block = outerBlock.getRelative(BlockFace.DOWN).getRelative(BlockFace.SOUTH);
                    Rail.Shape shape = getRailShape(block);
                    if (shape == Rail.Shape.ASCENDING_NORTH)
                        blocks.add(block);
                }

                break;
            }

            case EAST_WEST: {
                {
                    Block block = outerBlock.getRelative(BlockFace.EAST);
                    Rail.Shape shape = getRailShape(block);
                    if (shape == Rail.Shape.NORTH_WEST ||
                            shape == Rail.Shape.SOUTH_WEST ||
                            shape == Rail.Shape.EAST_WEST ||
                            shape == Rail.Shape.ASCENDING_EAST
                    ) blocks.add(block);
                }
                {
                    Block block = outerBlock.getRelative(BlockFace.DOWN).getRelative(BlockFace.EAST);
                    Rail.Shape shape = getRailShape(block);
                    if (shape == Rail.Shape.ASCENDING_WEST)
                        blocks.add(block);
                }

                {
                    Block block = outerBlock.getRelative(BlockFace.WEST);
                    Rail.Shape shape = getRailShape(block);
                    if (shape == Rail.Shape.EAST_WEST ||
                            shape == Rail.Shape.NORTH_EAST ||
                            shape == Rail.Shape.SOUTH_EAST ||
                            shape == Rail.Shape.ASCENDING_WEST
                    ) blocks.add(block);
                }
                {
                    Block block = outerBlock.getRelative(BlockFace.DOWN).getRelative(BlockFace.WEST);
                    Rail.Shape shape = getRailShape(block);
                    if (shape == Rail.Shape.ASCENDING_EAST)
                        blocks.add(block);
                }


                break;
            }
            case ASCENDING_EAST: {
                {
                    Block block = outerBlock.getRelative(BlockFace.EAST).getRelative(BlockFace.UP);
                    Rail.Shape shape = getRailShape(block);
                    if (shape == Rail.Shape.NORTH_WEST ||
                            shape == Rail.Shape.SOUTH_WEST ||
                            shape == Rail.Shape.EAST_WEST ||
                            shape == Rail.Shape.ASCENDING_EAST
                    ) blocks.add(block);
                }
                {
                    Block block = outerBlock.getRelative(BlockFace.WEST);
                    Rail.Shape shape = getRailShape(block);
                    if (shape == Rail.Shape.EAST_WEST ||
                            shape == Rail.Shape.NORTH_EAST ||
                            shape == Rail.Shape.SOUTH_EAST ||
                            shape == Rail.Shape.ASCENDING_WEST
                    ) blocks.add(block);
                }
                {
                    Block block = outerBlock.getRelative(BlockFace.DOWN).getRelative(BlockFace.EAST);
                    Rail.Shape shape = getRailShape(block);
                    if (shape == Rail.Shape.ASCENDING_WEST)
                        blocks.add(block);
                }
                {
                    Block block = outerBlock.getRelative(BlockFace.DOWN).getRelative(BlockFace.WEST);
                    Rail.Shape shape = getRailShape(block);
                    if (shape == Rail.Shape.ASCENDING_EAST)
                        blocks.add(block);
                }
                break;
            }
            case ASCENDING_WEST: {
                {
                    Block block = outerBlock.getRelative(BlockFace.EAST);
                    Rail.Shape shape = getRailShape(block);
                    if (shape == Rail.Shape.NORTH_WEST ||
                            shape == Rail.Shape.SOUTH_WEST ||
                            shape == Rail.Shape.EAST_WEST ||
                            shape == Rail.Shape.ASCENDING_EAST
                    ) blocks.add(block);
                }
                {
                    Block block = outerBlock.getRelative(BlockFace.WEST).getRelative(BlockFace.UP);
                    Rail.Shape shape = getRailShape(block);
                    if (shape == Rail.Shape.EAST_WEST ||
                            shape == Rail.Shape.NORTH_EAST ||
                            shape == Rail.Shape.SOUTH_EAST ||
                            shape == Rail.Shape.ASCENDING_WEST
                    ) blocks.add(block);
                }
                {
                    Block block = outerBlock.getRelative(BlockFace.DOWN).getRelative(BlockFace.EAST);
                    Rail.Shape shape = getRailShape(block);
                    if (shape == Rail.Shape.ASCENDING_WEST)
                        blocks.add(block);
                }
                {
                    Block block = outerBlock.getRelative(BlockFace.DOWN).getRelative(BlockFace.WEST);
                    Rail.Shape shape = getRailShape(block);
                    if (shape == Rail.Shape.ASCENDING_EAST)
                        blocks.add(block);
                }
                break;
            }
            case ASCENDING_NORTH: {
                {
                    Block block = outerBlock.getRelative(BlockFace.NORTH).getRelative(BlockFace.UP);
                    Rail.Shape shape = getRailShape(block);
                    if (shape == Rail.Shape.SOUTH_EAST ||
                            shape == Rail.Shape.SOUTH_WEST ||
                            shape == Rail.Shape.NORTH_SOUTH ||
                            shape == Rail.Shape.ASCENDING_NORTH
                    ) blocks.add(block);
                }
                {
                    Block block = outerBlock.getRelative(BlockFace.SOUTH);
                    Rail.Shape shape = getRailShape(block);
                    if (shape == Rail.Shape.NORTH_EAST ||
                            shape == Rail.Shape.NORTH_WEST ||
                            shape == Rail.Shape.NORTH_SOUTH ||
                            shape == Rail.Shape.ASCENDING_SOUTH
                    ) blocks.add(block);
                }
                {
                    Block block = outerBlock.getRelative(BlockFace.DOWN).getRelative(BlockFace.SOUTH);
                    Rail.Shape shape = getRailShape(block);
                    if (shape == Rail.Shape.ASCENDING_NORTH)
                        blocks.add(block);
                }
                {
                    Block block = outerBlock.getRelative(BlockFace.DOWN).getRelative(BlockFace.NORTH);
                    Rail.Shape shape = getRailShape(block);
                    if (shape == Rail.Shape.ASCENDING_SOUTH)
                        blocks.add(block);
                }
                break;
            }
            case ASCENDING_SOUTH: {
                {
                    Block block = outerBlock.getRelative(BlockFace.NORTH);
                    Rail.Shape shape = getRailShape(block);
                    if (shape == Rail.Shape.SOUTH_EAST ||
                            shape == Rail.Shape.SOUTH_WEST ||
                            shape == Rail.Shape.NORTH_SOUTH ||
                            shape == Rail.Shape.ASCENDING_NORTH
                    ) blocks.add(block);
                }
                {
                    Block block = outerBlock.getRelative(BlockFace.SOUTH).getRelative(BlockFace.UP);
                    Rail.Shape shape = getRailShape(block);
                    if (shape == Rail.Shape.NORTH_EAST ||
                            shape == Rail.Shape.NORTH_WEST ||
                            shape == Rail.Shape.NORTH_SOUTH ||
                            shape == Rail.Shape.ASCENDING_SOUTH
                    ) blocks.add(block);
                }
                {
                    Block block = outerBlock.getRelative(BlockFace.DOWN).getRelative(BlockFace.SOUTH);
                    Rail.Shape shape = getRailShape(block);
                    if (shape == Rail.Shape.ASCENDING_NORTH)
                        blocks.add(block);
                }
                {
                    Block block = outerBlock.getRelative(BlockFace.DOWN).getRelative(BlockFace.NORTH);
                    Rail.Shape shape = getRailShape(block);
                    if (shape == Rail.Shape.ASCENDING_SOUTH)
                        blocks.add(block);
                }
                break;
            }
            case SOUTH_EAST: {
                {
                    Block block = outerBlock.getRelative(BlockFace.SOUTH);
                    Rail.Shape shape = getRailShape(block);
                    if (shape == Rail.Shape.NORTH_EAST ||
                            shape == Rail.Shape.NORTH_WEST ||
                            shape == Rail.Shape.NORTH_SOUTH ||
                            shape == Rail.Shape.ASCENDING_SOUTH
                    ) blocks.add(block);
                }
                {
                    Block block = outerBlock.getRelative(BlockFace.DOWN).getRelative(BlockFace.SOUTH);
                    Rail.Shape shape = getRailShape(block);
                    if (shape == Rail.Shape.ASCENDING_NORTH)
                        blocks.add(block);
                }

                {
                    Block block = outerBlock.getRelative(BlockFace.EAST);
                    Rail.Shape shape = getRailShape(block);
                    if (shape == Rail.Shape.NORTH_WEST ||
                            shape == Rail.Shape.SOUTH_WEST ||
                            shape == Rail.Shape.EAST_WEST ||
                            shape == Rail.Shape.ASCENDING_EAST
                    ) blocks.add(block);
                }
                {
                    Block block = outerBlock.getRelative(BlockFace.DOWN).getRelative(BlockFace.EAST);
                    Rail.Shape shape = getRailShape(block);
                    if (shape == Rail.Shape.ASCENDING_WEST)
                        blocks.add(block);
                }
                break;
            }
            case SOUTH_WEST: {
                {
                    Block block = outerBlock.getRelative(BlockFace.SOUTH);
                    Rail.Shape shape = getRailShape(block);
                    if (shape == Rail.Shape.NORTH_EAST ||
                            shape == Rail.Shape.NORTH_WEST ||
                            shape == Rail.Shape.NORTH_SOUTH ||
                            shape == Rail.Shape.ASCENDING_SOUTH
                    ) blocks.add(block);
                }
                {
                    Block block = outerBlock.getRelative(BlockFace.DOWN).getRelative(BlockFace.SOUTH);
                    Rail.Shape shape = getRailShape(block);
                    if (shape == Rail.Shape.ASCENDING_NORTH)
                        blocks.add(block);
                }

                {
                    Block block = outerBlock.getRelative(BlockFace.WEST);
                    Rail.Shape shape = getRailShape(block);
                    if (shape == Rail.Shape.EAST_WEST ||
                            shape == Rail.Shape.NORTH_EAST ||
                            shape == Rail.Shape.SOUTH_EAST ||
                            shape == Rail.Shape.ASCENDING_WEST
                    ) blocks.add(block);
                }
                {
                    Block block = outerBlock.getRelative(BlockFace.DOWN).getRelative(BlockFace.WEST);
                    Rail.Shape shape = getRailShape(block);
                    if (shape == Rail.Shape.ASCENDING_EAST)
                        blocks.add(block);
                }
                break;
            }
            case NORTH_WEST: {
                {
                    Block block = outerBlock.getRelative(BlockFace.WEST);
                    Rail.Shape shape = getRailShape(block);
                    if (shape == Rail.Shape.EAST_WEST ||
                            shape == Rail.Shape.NORTH_EAST ||
                            shape == Rail.Shape.SOUTH_EAST ||
                            shape == Rail.Shape.ASCENDING_WEST
                    ) blocks.add(block);
                }
                {
                    Block block = outerBlock.getRelative(BlockFace.DOWN).getRelative(BlockFace.WEST);
                    Rail.Shape shape = getRailShape(block);
                    if (shape == Rail.Shape.ASCENDING_EAST)
                        blocks.add(block);
                }

            }
            case NORTH_EAST: {
                {
                    Block block = outerBlock.getRelative(BlockFace.NORTH);
                    Rail.Shape shape = getRailShape(block);
                    if (shape == Rail.Shape.SOUTH_EAST ||
                            shape == Rail.Shape.SOUTH_WEST ||
                            shape == Rail.Shape.NORTH_SOUTH ||
                            shape == Rail.Shape.ASCENDING_NORTH
                    ) blocks.add(block);
                }
                {
                    Block block = outerBlock.getRelative(BlockFace.DOWN).getRelative(BlockFace.NORTH);
                    Rail.Shape shape = getRailShape(block);
                    if (shape == Rail.Shape.ASCENDING_SOUTH)
                        blocks.add(block);
                }
                {
                    Block block = outerBlock.getRelative(BlockFace.EAST);
                    Rail.Shape shape = getRailShape(block);
                    if (shape == Rail.Shape.NORTH_WEST ||
                            shape == Rail.Shape.SOUTH_WEST ||
                            shape == Rail.Shape.EAST_WEST ||
                            shape == Rail.Shape.ASCENDING_EAST
                    ) blocks.add(block);
                }
                {
                    Block block = outerBlock.getRelative(BlockFace.DOWN).getRelative(BlockFace.EAST);
                    Rail.Shape shape = getRailShape(block);
                    if (shape == Rail.Shape.ASCENDING_WEST)
                        blocks.add(block);
                }
            }
        }
        return blocks;
    }

    public static Block getLastConnectedRail(Block first, Block second) {
        Block init = first;
        outer:
        while (true) {
            List<Block> connectedRails = RailUtils.getConnectedRails(second);
            for (Block connectedRail : connectedRails) {
                if (!connectedRail.equals(first) && !connectedRail.equals(init)) {
                    first = second;
                    second = connectedRail;
                    continue outer;
                }
            }
            break;
        }
        return second;
    }
}
