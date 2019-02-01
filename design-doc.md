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
    To Be Determined
    