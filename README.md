
#### App Overview
* The App has locally generated dummy data. The dummy data is created only on the first launch of the app.
* Realm is used to locally store data
* Dagger2 is extensively used as the DI framework
* Greenrobot's event bus is used as the delivery channel of data from presenters to the activity and so forth.

## Package Structure
* _dagger_ consists of dagger based configuration classes
* _events_ consists of POJOs used by EventBus for transferring data
* _locationapi_ has the Tracking Module's location code
* _services_ has the Service which is responsible for tracking of the user in the track module
* _ui_ consists of UI based packages and classes which are self explanatory.

#### Known Bugs
- The location updates are not being delivered from the GoogleApiClient which in turn isnt plotting the user tracking! :(

### What is this repository for? ###
This document contains the details of the release of modules in the Order Management android app.

### How do I get set up? ###
* Android studio
* Android SDK
* Basic Knowledge of Gradle
* The project uses a custom signing key which is present the project itself.

### Who do I talk to? ###

* iparkarbasit@gmail.com 

