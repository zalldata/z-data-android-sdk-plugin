/*
 * Created by guo on 2018/12/01.
 * Copyright 2015－2021 Zall Data Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zalldata.analytics.android.plugin

import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.Opcodes


class ZallAnalyticsSDKHookConfig {

    HashMap<String, HashMap<String, ArrayList<ZallAnalyticsMethodCell>>> methodCells = new HashMap<>()

    void disableIMEI(String methodName) {
        def imei = new ZallAnalyticsMethodCell('getIMEI', '(Landroid/content/Context;)Ljava/lang/String;', 'createGetIMEI')
        def deviceID = new ZallAnalyticsMethodCell('getDeviceID', '(Landroid/content/Context;I)Ljava/lang/String;', 'createGetDeviceID')
        def imeiMethods = [imei, deviceID]
        def imeiMethodCells = new HashMap<String, ArrayList<ZallAnalyticsMethodCell>>()
        imeiMethodCells.put("com/zalldata/analytics/android/sdk/util/ZallDataUtils", imeiMethods)
        methodCells.put(methodName, imeiMethodCells)
    }

    void disableAndroidID(String methodName) {
        def androidID = new ZallAnalyticsMethodCell('getAndroidID', '(Landroid/content/Context;)Ljava/lang/String;', 'createGetAndroidID')
        def androidIDMethods = [androidID]
        def androidIdMethodCells = new HashMap<String, ArrayList<ZallAnalyticsMethodCell>>()
        androidIdMethodCells.put('com/zalldata/analytics/android/sdk/util/ZallDataUtils', androidIDMethods)
        methodCells.put(methodName, androidIdMethodCells)
    }

    void disableLog(String methodName) {
        def info = new ZallAnalyticsMethodCell('info', '(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V', "createZALogInfo")
        def printStackTrace = new ZallAnalyticsMethodCell('printStackTrace', '(Ljava/lang/Exception;)V', "createPrintStackTrack")
        def zALogMethods = [info, printStackTrace]
        def zALogMethodCells = new HashMap<String, ArrayList<ZallAnalyticsMethodCell>>()
        zALogMethodCells.put('com/zalldata/analytics/android/sdk/ZALog', zALogMethods)
        methodCells.put(methodName, zALogMethodCells)
    }

    void disableJsInterface(String methodName) {
        def showUpWebView = new ZallAnalyticsMethodCell("showUpWebView", '(Landroid/webkit/WebView;Lorg/json/JSONObject;ZZ)V', "createShowUpWebViewFour")
        def showUpX5WebView = new ZallAnalyticsMethodCell("showUpX5WebView", '(Ljava/lang/Object;Lorg/json/JSONObject;ZZ)V', "createShowUpX5WebViewFour")
        def showUpX5WebView2 = new ZallAnalyticsMethodCell("showUpX5WebView", '(Ljava/lang/Object;Z)V', "createShowUpX5WebViewTwo")
        def zallDataAPIMethods = [showUpWebView, showUpX5WebView, showUpX5WebView2]
        def zallDataAPIMethodCells = new HashMap<String, ArrayList<ZallAnalyticsMethodCell>>()
        zallDataAPIMethodCells.put('com/zalldata/analytics/android/sdk/ZallDataAPI', zallDataAPIMethods)
        methodCells.put(methodName, zallDataAPIMethodCells)
    }

    void disableMacAddress(String methodName) {
        def macAddress = new ZallAnalyticsMethodCell('getMacAddress', '(Landroid/content/Context;)Ljava/lang/String;', 'createGetMacAddress')
        def macMethods = [macAddress]
        def macMethodCells = new HashMap<String, ArrayList<ZallAnalyticsMethodCell>>()
        macMethodCells.put("com/zalldata/analytics/android/sdk/util/ZallDataUtils", macMethods)
        methodCells.put(methodName, macMethodCells)
    }

    void disableCarrier(String methodName) {
        def carrier = new ZallAnalyticsMethodCell('getCarrier', '(Landroid/content/Context;)Ljava/lang/String;', 'createGetCarrier')
        def macMethods = [carrier]
        def macMethodCells = new HashMap<String, ArrayList<ZallAnalyticsMethodCell>>()
        macMethodCells.put("com/zalldata/analytics/android/sdk/util/ZallDataUtils", macMethods)
        methodCells.put(methodName, macMethodCells)
    }

    //todo 扩展

    void createGetIMEI(ClassVisitor classVisitor, ZallAnalyticsMethodCell methodCell) {
        def mv = classVisitor.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, methodCell.name, methodCell.desc, null, null)
        mv.visitCode()
        mv.visitLdcInsn("")
        mv.visitInsn(Opcodes.ARETURN)
        mv.visitMaxs(1, 1)
        mv.visitEnd()
    }

    void createGetAndroidID(ClassVisitor classVisitor, ZallAnalyticsMethodCell methodCell) {
        def mv = classVisitor.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, methodCell.name, methodCell.desc, null, null)
        mv.visitCode()
        mv.visitLdcInsn("")
        mv.visitInsn(Opcodes.ARETURN)
        mv.visitMaxs(1, 1)
        mv.visitEnd()
    }

    void createZALogInfo(ClassVisitor classVisitor, ZallAnalyticsMethodCell methodCell) {
        def mv = classVisitor.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, methodCell.name, methodCell.desc, null, null)
        mv.visitCode()
        mv.visitInsn(Opcodes.RETURN)
        mv.visitMaxs(0, 3)
        mv.visitEnd()
    }

    void createPrintStackTrack(ClassVisitor classVisitor, ZallAnalyticsMethodCell methodCell) {
        def mv = classVisitor.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, methodCell.name, methodCell.desc, null, null)
        mv.visitCode()
        mv.visitInsn(Opcodes.RETURN)
        mv.visitMaxs(0, 1)
        mv.visitEnd()
    }

    void createShowUpWebViewFour(ClassVisitor classVisitor, ZallAnalyticsMethodCell methodCell) {
        def mv = classVisitor.visitMethod(Opcodes.ACC_PUBLIC, methodCell.name, methodCell.desc, null, null)
        mv.visitCode()
        mv.visitInsn(Opcodes.RETURN)
        mv.visitMaxs(0, 5)
        mv.visitEnd()
    }

    void createShowUpX5WebViewFour(ClassVisitor classVisitor, ZallAnalyticsMethodCell methodCell) {
        def mv = classVisitor.visitMethod(Opcodes.ACC_PUBLIC, methodCell.name, methodCell.desc, null, null)
        mv.visitCode()
        mv.visitInsn(Opcodes.RETURN)
        mv.visitMaxs(0, 5)
        mv.visitEnd()
    }

    void createShowUpX5WebViewTwo(ClassVisitor classVisitor, ZallAnalyticsMethodCell methodCell) {
        def mv = classVisitor.visitMethod(Opcodes.ACC_PUBLIC, methodCell.name, methodCell.desc, null, null)
        mv.visitCode()
        mv.visitInsn(Opcodes.RETURN)
        mv.visitMaxs(0, 3)
        mv.visitEnd()
    }

    void createGetMacAddress(ClassVisitor classVisitor, ZallAnalyticsMethodCell methodCell) {
        def mv = classVisitor.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, methodCell.name, methodCell.desc, null, null)
        mv.visitCode()
        mv.visitLdcInsn("")
        mv.visitInsn(Opcodes.ARETURN)
        mv.visitMaxs(1, 1)
        mv.visitEnd()
    }

    void createGetCarrier(ClassVisitor classVisitor, ZallAnalyticsMethodCell methodCell) {
        def mv = classVisitor.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, methodCell.name, methodCell.desc, null, null)
        mv.visitCode()
        mv.visitLdcInsn("")
        mv.visitInsn(Opcodes.ARETURN)
        mv.visitMaxs(1, 1)
        mv.visitEnd()
    }

    void createGetDeviceID(ClassVisitor classVisitor, ZallAnalyticsMethodCell methodCell) {
        def mv = classVisitor.visitMethod(Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, methodCell.name, methodCell.desc, null, null)
        mv.visitCode()
        mv.visitLdcInsn("")
        mv.visitInsn(Opcodes.ARETURN)
        mv.visitMaxs(1, 1)
        mv.visitEnd()
    }

    //todo 扩展

}
