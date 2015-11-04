AndroidMvvm
===============
Explore an Android app that:

* Has (almost) no business logic in the Activity / Fragment.
* Has fully unit testable ViewModels, including rotation (sorry Espresso).
* Uses Android Data Binding.
* Has minimum Espresso testing, solely to verify the bindings.


Sample App
=============
### Main Screen

![Main Screen](../screenshots/screenshots/main_screen.png?raw=true)

**Button Clicks**  
Goes to the Click Count screen.

**RecyclerView**  
Goes to the Android Versions screen.

**@hiBrianLee**  
Goes to https://twitter.com/hiBrianLee


### Click Count

![Click Count](../screenshots/screenshots/click_count.png?raw=true)

**Click count: 0**  
Shows the total number of clicks

**Click**  
Increments the number of clicks.

**@hiBrianLee**  
Goes to https://twitter.com/hiBrianLee


### Android Versions

![Android Versions](../screenshots/screenshots/android_versions.png?raw=true)

Lists the different Android versions in a RecyclerView. Clicking on an item will highlight it.

**@hiBrianLee**  
Goes to https://twitter.com/hiBrianLee


License
=======
    Copyright (c) 2015 Brian Lee (@hiBrianLee)

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
