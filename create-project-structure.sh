#!/bin/bash

# Créer la structure complète des packages
mkdir -p domain/src/main/java/com/conway/game/domain/{model,port}
mkdir -p domain/src/test/java/com/conway/game/domain/{model,port}

mkdir -p application/src/main/java/com/conway/game/application/{usecase,service,port}
mkdir -p application/src/test/java/com/conway/game/application/{usecase,service}

mkdir -p infrastructure/src/main/java/com/conway/game/infrastructure/{persistence,config}
mkdir -p infrastructure/src/test/java/com/conway/game/infrastructure/persistence

mkdir -p views/src/main/java/com/conway/game/views/{swing/panel,swing/controller}
mkdir -p views/src/main/resources

echo "Structure créée avec succès!"
