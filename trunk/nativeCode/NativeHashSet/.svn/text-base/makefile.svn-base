linux:
	g++ -fPIC -c -o test NativeSetWrapper.cpp -I$(JAVA_HOME)/include
	g++ -shared -o ../libNativeSetWrapper.so test
	rm test

mac:
	g++ -c -I/System/Library/Frameworks/JavaVM.framework/Headers NativeSetWrapper.cpp
	g++ -dynamiclib -o libNativeSetWrapper.jnilib NativeSetWrapper.o -framework JavaVM
