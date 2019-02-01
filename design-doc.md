Design Notes:
  Operation:
    This will work in 5 passes, either:
      1) First pass just generates terrain, period - all stone
      2) Next pass hands off to all registered "type" modifiers
      3) Do "type" specific surface material generation
      4) Pass after hands off to "features" (villages, lakes, etc...)
      5) OreSpawn
      6) Surface feature (flowers, trees, etc...)
    or:
      1) First pass just generates terrain, period - all stone
      2) Next pass hands off to all registered "type" modifiers
      3) Do "type" specific surface material generation
      4) Surface features (flowers, trees, etc...)
      5) Pass after hands off to "features" (villages, lakes, etc...)
      6) OreSpawn

  JSON Static Data:
  * Colors are ARGB
  * Features are things that are added generation, like caves, mineshafts, villages, etc...
  * generator-params is defined by each, separate generator
  ```json
    {
      "name": "dimension name",
      "dimension-params": {
        "cloud_height": 128,
        "fog-color": #DEADBEEF,
        "features": [ "mineshafts", "villages", "caves" ],
        "sea-level": 60,
        "sky-color": #DEADBEEF,
        "has-night": true,
        "sun-color": #DEADBEEF,
        "is-void": false,
        "is-hell": false
      },
      "generator": "custom",
      "generator-params": {
         ...
      },
      "generator-entry": "mymod:generator-core.js"
    }
  ```
  
  JS API:
  * Needs (3 thru 6 as constants?):
    1) DimensionType object (expose DimensionType.register)
    2) NoiseProvider
       1) NoiseGenerator (base in some form)
       2) NoiseGeneratorImproved
       3) NoiseGeneratorOctaves
       4) NoiseGeneratorPerlin
       5) NoiseGeneratorSimplex
    3) MapGen
       1) MapGenBase (duh)
       2) MapGenCaves
       3) MapGenCavesHell
       4) MapGenRavine
    4) any/all of net.minecraft.world.gen.feature
    5) any/all of net.minecraft.world.gen.structure
    6) any/all of net.minecraft.world.gen.layer
    7) net.minecraft.world.chunk.ChunkPrimer (in some form)
    8) net.minecraft.world.biome.* (in some form)
    9) MapRegionType (see gen pass #2 in operation description)
    10) BlockRegistry/ItemRegistry in some form ?
    11) User-registered structure gens in some form ?
  * Top-level class structure:
    1) Noise
      * Provides noise functions for use - at this time we've only got the "Notch" ones handy, but more can be added.
      * Noise.Improved
      * Noise.Octaves
      * Noise.Perlin
      * Noise.Simplex
    2) Blocks
      * Provides access to the Block Registry for getting various blocks for placement
      * has
      * get
    3) Items
      * Provides access to the Item Registry for getting various blocks for placement
      * has
      * get
    4)Chunk
      * Access to the currently under work chunk
      * setBlockState
  * Non-final for any listed part, this is a rough outline
  * The rest of the API is To Be Determined
  