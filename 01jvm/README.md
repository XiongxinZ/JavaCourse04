Java Memory Architecture

参考：https://medium.com/platform-engineer/understanding-java-memory-model-1d0863f6d973

![JVM框架](https://miro.medium.com/max/1286/0*aO7jvEaMLhADKTqa)

![JVM堆内存](https://miro.medium.com/max/1400/0*rzQ6-DyP-2gjiua7)

![JVM非堆内存](https://miro.medium.com/max/1400/0*28wQjfFfyVZURF1D)

解释：

- XmsSetting — initial Heap size
- XmxSetting — maximum Heap size
- XX:NewSizeSetting — new generation heap size
- XX:MaxNewSizeSetting — maximum New generation heap size
- XX:MaxPermGenSetting — maximum size of Permanent generation
- XX:SurvivorRatioSetting — new heap size ratios (e.g. if Young Gen size is 10m and memory switch is –XX:SurvivorRatio=2, then 5m will be reserved for Eden space and 2.5m each for both Survivor spaces, default value = 8)
- XX:NewRatio — providing ratio of Old/New Gen sizes (default value = 2)
