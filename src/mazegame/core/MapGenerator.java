// Map generator is subclasses by other classes to generate different
// maps: fixed maps like the TestMap, or other mover advanced
// algorithms that subclass form MapGeneratorAlgo (a subclass of this
// class).

package mazegame.core;

import mazegame.core.Map;

interface MapGenerator {
    Map generateMap();
}
