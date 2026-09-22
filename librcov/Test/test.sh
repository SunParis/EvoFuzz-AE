
export RCOV_FOCUS_FILES='opto'
export BLOCK_EXEC_FILE='javac'

# If TEST_JAVA_HOME is not set
if [ -z "$TEST_JAVA_HOME" ]; then
    # if exist '../../jvm/jdk17u-clang/build/rcov-release/jdk/'
    if [ -d "../../jvm/jdk17u-clang/build/rcov-release/jdk/" ]; then
        export TEST_JAVA_HOME="../../jvm/jdk17u-clang/build/rcov-release/jdk/"
    elif [ -d "../../../jvm/jdk17u-clang/build/rcov-release/jdk/" ]; then
        export TEST_JAVA_HOME="../../../jvm/jdk17u-clang/build/rcov-release/jdk/"
    fi
fi
if [ -z "$TEST_JAVA_HOME" ]; then
    echo "TEST_JAVA_HOME is not set. Please set TEST_JAVA_HOME to the JDK you want to test."
    exit 1
else
    $TEST_JAVA_HOME/bin/javac Test.java
    echo "Finish compilation, start running test"
    time $TEST_JAVA_HOME/bin/java Test > stdout.log 2> stderr.log
    echo "Finish running test with TEST_JAVA_HOME"
    echo "==============================================================="
    export OUT_PTR=1
    time $TEST_JAVA_HOME/bin/java Test > stdout2.log 2> stderr2.log
    echo "Finish running test with TEST_JAVA_HOME,OUT_PTR"
    unset OUT_PTR
fi
echo "==============================================================="

if [ -z "$FAST_DEBUG_JAVA_HOME" ]; then
    if [ -d "../../jvm/jdk17u/build/fastdebug" ]; then
        export FAST_DEBUG_JAVA_HOME="../../jvm/jdk17u/build/fastdebug/jdk"
    elif [ -d "../../../jvm/jdk17u/build/fastdebug" ]; then
        export FAST_DEBUG_JAVA_HOME="../../../jvm/jdk17u/build/fastdebug/jdk"
    elif [ -d "../../jvm/jdk17u/build/fastdebug-coverage" ]; then
        export FAST_DEBUG_JAVA_HOME="../../jvm/jdk17u/build/fastdebug-coverage/jdk"
    elif [ -d "../../../jvm/jdk17u/build/fastdebug-coverage" ]; then
        export FAST_DEBUG_JAVA_HOME="../../../jvm/jdk17u/build/fastdebug-coverage/jdk"
    fi
fi
if [ -z "$FAST_DEBUG_JAVA_HOME" ]; then
    echo "FAST_DEBUG_JAVA_HOME is not set. Please set FAST_DEBUG_JAVA_HOME to the JDK you want to test."
    echo "Skip checking FAST_DEBUG_JAVA_HOME"
else
    time $FAST_DEBUG_JAVA_HOME/bin/java Test > stdout3.log 2> stderr3.log
    echo "Finish running test with FAST_DEBUG_JAVA_HOME"
    time $FAST_DEBUG_JAVA_HOME/bin/java -XX:PrintIdealGraphFile=graph.log -XX:PrintIdealGraphLevel=4 Test > stdout3.log 2> stderr3.log
    echo "Finish running test with FAST_DEBUG_JAVA_HOME,PrintIdealGraphFile,PrintIdealGraphLevel"
fi
echo "==============================================================="

if [ -z "$RELEASE_JAVA_HOME" ]; then
    if [ -d "../../jvm/jdk17u/build/release" ]; then
        export RELEASE_JAVA_HOME="../../jvm/jdk17u/build/release/jdk"
    elif [ -d "../../../jvm/jdk17u/build/release" ]; then
        export RELEASE_JAVA_HOME="../../../jvm/jdk17u/build/release/jdk"
    elif [ -d "../../jvm/jdk17u/build/release" ]; then
        export RELEASE_JAVA_HOME="../../jvm/jdk17u/build/release/jdk"
    elif [ -d "../../../jvm/jdk17u/build/release" ]; then
        export RELEASE_JAVA_HOME="../../../jvm/jdk17u/build/release/jdk"
    fi
fi
if [ -z "$RELEASE_JAVA_HOME" ]; then
    echo "RELEASE_JAVA_HOME is not set. Please set RELEASE_JAVA_HOME to the JDK you want to test."
    echo "Skip checking RELEASE_JAVA_HOME"
else
    time $RELEASE_JAVA_HOME/bin/java Test > stdout4.log 2> stderr4.log
    echo "Finish running test with RELEASE_JAVA_HOME"
fi
echo "==============================================================="

# addr2line -e ../jvm/jdk17u-clang/build/rcov-release/images/jdk/lib/server/libjvm.so 0x588483
