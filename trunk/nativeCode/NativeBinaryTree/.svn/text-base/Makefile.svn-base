OBJS = BtreeNode.o BinaryTree.o
SRC = $(OBJS:.o=.cpp)

#PLATFORM = __APPLE_MAC_OS__
PLATFORM = __LINUX__
#PLATFORM = __SOLARIS__

CC = g++ -DDEBUG_MODE -D$(PLATFORM) -g -Wno-deprecated
#CC = g++ -DDEBUG_MODE -D$(PLATFORM) -I$(INCLUDE) -g -pg -Wno-deprecated

CFLAGS    += -D __STDC_LIMIT_MACROS -D __STDC_FORMAT_MACROS -fPIC
LFLAGS    += -lz


#
# Rules
#

all: $(OBJS)

-include $(OBJS:.o=.d)
	
BtreeNode.o: BtreeNode.cpp BtreeNode.h
	$(CC) $(CFLAGS) -c $<

BinaryTree.o: BinaryTree.cpp BinaryTree.h BtreeNode.h
	$(CC) $(CFLAGS) -c $<

test.o: test.cpp
	$(CC) $(CFLAGS) -c $<

linux: NativeBinaryTree.cpp
		$(CC) $(CFLAGS) -c -I$(JAVA_HOME)/include -o temp $<
		g++ -shared -o ../libNativeBinaryTree.so temp BtreeNode.o BinaryTree.o
		rm temp
		rm -f *.o

mac: NativeBinaryTree.cpp
		$(CC) $(CFLAGS) -c -I/System/Library/Frameworks/JavaVM.framework/Headers 
		g++ -dynamiclib -o libNativeBinaryTree.jnilib NativeBinaryTree.o -framework JavaVM -BtreeNode.o BinaryTree.o

test: test.o $(OBJS)
	$(CC) $(CFLAGS) $(LFLAGS) -o $@ $(OBJS) test.o

# remove compilation products
.PHONY: clean
clean:
	rm -f *.o test
