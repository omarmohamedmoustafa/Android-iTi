#include <jni.h>
#include <string>

extern "C" JNIEXPORT void JNICALL
Java_com_example_jni_JavaMiddleWare_computeFactorial(
        JNIEnv* env,
        jobject thiz,
        jint n) {
    // Calculate factorial
    long long result = 1;
    if (n < 0) {
        result = -1;
    } else if (n == 0 || n == 1) {
        result = 1;
    } else {
        for (jint i = 2; i <= n; ++i) {
            result *= i;
        }
    }
    // Find JavaMiddleWare class
    jclass middlewareClass = env->GetObjectClass(thiz);
    if (middlewareClass == nullptr) return;
    // Find the displayResult method
    jmethodID displayMethod = env->GetMethodID(middlewareClass, "displayResult", "(Ljava/lang/String;)V");
    if (displayMethod == nullptr) return;

    std::string resultStr = (result == -1) ? "Error: Negative number" :
                            std::to_string(result);
    jstring jResult = env->NewStringUTF(resultStr.c_str());
    // Call Java method to pass result
    env->CallVoidMethod(thiz, displayMethod, jResult);
}