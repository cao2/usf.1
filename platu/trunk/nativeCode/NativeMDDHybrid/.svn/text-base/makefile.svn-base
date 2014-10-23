include Makefile-64bit.inc

INCLUDE=-I$(CUDD_INCLUDE)

OBJS=$(CUDD_OBJ1) \
		 $(CUDD_OBJ2) \
		 $(CUDD_OBJ3) \
		 $(CUDD_OBJ4) \
		 $(CUDD_OBJ5) \
		 $(CUDD_OBJ6)


linux:  
	g++ -Wall -fPIC -c -o temp -I$(JAVA_HOME)/include NativeHybridMDDWrapper.cpp
	g++ -Wall -shared -o ../libNativeHybridMDDWrapper.so temp $(OBJS)
	rm temp
mac:
	g++ -o temp -I/System/Library/Frameworks/JavaVM.framework/Headers NativeHybridMDDWrapper.cpp
	g++ -dynamiclib -o ../libNativeHybridMDDWrapper.jnilib temp $(OBJS) -framework JavaVM
	rm temp
