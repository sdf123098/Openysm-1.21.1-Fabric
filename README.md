<div align="center">
  <img src="images/144725232_p0.png" alt="banner"/>
  <p>图片作者：「pixiv」<a href="https://www.pixiv.net/users/76344429">法师来自未来</a></p>
  <h1>Fox Model Loader / 绯绯狐的模组加载</h1>
  <p>基于 <a href="https://gitgud.io/NoSteveModel/OpenYSM">OpenYSM</a> 项目的 1.21+ 迁移版本，适用于 <strong>Fabric</strong> 加载器</p>
</div>

## 源码地址

本项目基于上游项目 OpenYSM 进行迁移：

- **上游源码**: [https://gitgud.io/NoSteveModel/OpenYSM](https://gitgud.io/NoSteveModel/OpenYSM)

## 关于 YSM 与 OpenYSM

YSM 与 OpenYSM 之间的过往纠葛并非本项目的重点。绯绯狐的模组加载仅专注于将 OpenYSM 迁移至 Minecraft 1.21+ 平台，为社区提供一个可用的模型加载工具。请勿在本仓库中讨论与开发无关的争议话题。

## 说明

本仓库目前包含了基于 OpenYSM 的 1.21+的Fabric 迁移版本。

这里要体现的是 AI Agent 在移植方面的重大成功，在 deepseek-v4-pro「1m」与 Claude Code 的强强配合下，原本需要一周时间的移植工作，成功压缩至一天（这就是不讨论社区的理由）。这说明一件事，我们普通人，自己就可以迁移模组，甚至开发模组。这一次迁移，花费了我 10 CNY，token（词元）消耗数为一亿，在此过程中，解决了从 1.20.1 迁移至 1.21.1 的各种困难，包括但不限于 mixin 变更、Mojang 在 1.21.1 改变变量所带来的一系列问题。帮助我一个不懂 Java 的普通人，解决了大量的依赖、环境变量，以及运行问题。这足以说明，迁移模组本身，是完全可行的。只需要动动手指，你也能迁移成功。

这里建议，迁移一般模组用我上述的组合，便可在一天内完成。DeepSeek 的低成本优势，配合 Claude Code 的高命中，让每个人都能花一点点 token 所需的小钱，便能完成这个费时费力还需要熟悉 Java、Fabric 的工作。这就是 AI Agent 带给我们的意义：提高生产力，让更多人能真正发挥自己的想法。

**请注意：项目并非 Production Ready，可能存在命名语义错误、渲染错误等问题，如果您在使用过程中遇到了任何问题请打开 Issue 反馈，最好附带截图和可能的报错日志。**
