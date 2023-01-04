
db.createCollection( 'players', {validator: {$jsonSchema: {bsonType: 'object',required: [         'name',          'avg_success',          'games'],properties: {name: {bsonType: 'string'},avg_success: {bsonType: 'double'},games: {bsonType: 'object',
required: [         'id',          'success',          'rolls'],properties: {id: {bsonType: 'objectId'},success: {bsonType: 'decimal'},rolls: {bsonType: 'object',
required: [         'id',          'dice 1',          'dice 2'],properties: {id: {bsonType: 'objectId'},dice 1: {bsonType: 'int'},dice 2: {bsonType: 'int'}}}}}}         }      }});  