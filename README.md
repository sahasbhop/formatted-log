# Formatted Log

Android Log wrapper class that supports string formatting and automatically generate a tag.

## Usage
```java
@Override
public void onCreate(Bundle savedInstanceState) {
	FLog.v("ENTER");
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);

	FLog.d("saveInstanceState: %s", savedInstanceState);
	FLog.v("EXIT");
}
```
Output
```
2015-22-03 17:13:06.165: VERBOSE/MainActivity(1225): onCreate # ENTER
2015-22-03 17:13:06.200: DEBUG/MainActivity(1225): onCreate # saveInstanceState: null
2015-22-03 17:13:06.204: VERBOSE/MainActivity(1225): onCreate # EXIT
```

## License
Copyright 2015 Sahasbhop Suvadhanabhakdi.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.