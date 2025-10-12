# Git 提交规范指南

## 📋 目录

- [基本原则](#基本原则)
- [Commit Message 格式](#commit-message-格式)
- [提交类型说明](#提交类型说明)
- [本项目专用规范](#本项目专用规范)
- [提交流程](#提交流程)
- [常用命令速查](#常用命令速查)
- [最佳实践](#最佳实践)
- [常见问题](#常见问题)

---

## 基本原则

### ✅ 好的提交应该：
1. **原子性**：每次提交只做一件事
2. **清晰性**：提交信息清楚描述改动内容
3. **完整性**：提交的代码能够编译和运行
4. **有意义**：避免无意义的提交信息（如"update"、"fix"）

### ❌ 避免：
1. 一次提交包含多个不相关的改动
2. 提交信息过于简单或不清楚
3. 提交了临时文件、编译产物或敏感信息
4. 频繁的"fix typo"、"oops"等修复性提交

---

## Commit Message 格式

### 标准格式（推荐）

```
<类型>(<范围>): <简短描述>

<详细描述>（可选，空一行）

<页脚>（可选，如关闭issue）
```

### 格式说明

- **类型**（必需）：说明提交的类别
- **范围**（可选）：说明提交影响的范围
- **简短描述**（必需）：不超过50个字符的简要说明
- **详细描述**（可选）：详细说明本次提交的内容
- **页脚**（可选）：关闭issue或重大变更说明

### 示例

```bash
# 简单格式
feat: 添加用户登录功能

# 带范围
feat(auth): 添加JWT令牌验证

# 完整格式
feat(array): 完成两数之和题目

使用哈希表实现O(n)时间复杂度的解法：
- 添加暴力解法和哈希表解法
- 添加详细的注释和复杂度分析
- 编写单元测试用例

Closes #1
```

---

## 提交类型说明

### 核心类型

| 类型 | 说明 | 示例 |
|------|------|------|
| **feat** | 新功能 | `feat(array): 完成最大子数组和题目` |
| **fix** | 修复 bug | `fix(tree): 修复二叉树遍历空指针异常` |
| **docs** | 文档变更 | `docs: 更新 README 进度统计` |
| **style** | 代码格式（不影响代码运行） | `style: 统一代码缩进为4个空格` |
| **refactor** | 重构（不增加功能，不修复bug） | `refactor(utils): 重构链表工具类` |
| **perf** | 性能优化 | `perf(dp): 优化动态规划空间复杂度` |
| **test** | 测试相关 | `test(array): 添加边界测试用例` |
| **chore** | 构建/工具/依赖变更 | `chore: 升级JUnit版本到5.10` |
| **revert** | 回滚之前的提交 | `revert: 撤销"feat: 添加用户登录"` |

### 扩展类型（可选）

| 类型 | 说明 | 示例 |
|------|------|------|
| **build** | 构建系统变更 | `build: 修改Maven打包配置` |
| **ci** | CI/CD 配置变更 | `ci: 添加GitHub Actions配置` |
| **merge** | 合并分支 | `merge: 合并dev分支到main` |

---

## 提交流程

### 标准流程

```bash
# 1. 查看当前状态
git status

# 2. 查看具体修改内容
git diff

# 3. 暂存需要提交的文件
git add <file>              # 添加指定文件
git add .                   # 添加所有变更（谨慎使用）
git add -p                  # 交互式添加（推荐）

# 4. 再次确认暂存内容
git diff --staged

# 5. 提交
git commit -m "类型: 简短描述"
# 或使用编辑器编写详细信息
git commit

# 6. 推送到远程
git push origin main
```

---

## 常用命令速查

### 基础命令

```bash
# 初始化仓库
git init
git clone <url>

# 查看状态
git status                  # 查看工作区状态
git log                     # 查看提交历史
git log --oneline           # 简洁查看历史
git log --oneline -5        # 查看最近5次提交
git log --graph --all       # 图形化查看所有分支

# 查看差异
git diff                    # 工作区 vs 暂存区
git diff --staged           # 暂存区 vs 最新提交
git diff HEAD               # 工作区 vs 最新提交
git diff <file>             # 查看指定文件的差异
```

### 暂存和提交

```bash
# 暂存文件
git add <file>              # 添加指定文件
git add <directory>         # 添加整个目录
git add .                   # 添加所有变更
git add *.java              # 添加所有Java文件
git add -p                  # 交互式添加（部分暂存）

# 提交
git commit -m "message"     # 提交并附上信息
git commit                  # 打开编辑器编写详细信息
git commit -am "message"    # 暂存所有已跟踪文件并提交
git commit --amend          # 修改最后一次提交
```

### 撤销操作

```bash
# 撤销工作区修改
git restore <file>          # 撤销指定文件的修改
git restore .               # 撤销所有修改
git checkout -- <file>      # 旧版本命令

# 取消暂存
git restore --staged <file> # 取消暂存指定文件
git reset HEAD <file>       # 旧版本命令

# 撤销提交
git reset HEAD~1            # 撤销最后一次提交（保留修改）
git reset --soft HEAD~1     # 撤销提交，修改保留在暂存区
git reset --hard HEAD~1     # ⚠️ 撤销提交并丢弃修改（危险）

# 回滚到指定提交
git revert <commit-hash>    # 创建新提交来撤销指定提交
```

### 远程操作

```bash
# 查看远程仓库
git remote -v               # 查看远程仓库地址
git remote show origin      # 查看远程仓库详细信息

# 添加远程仓库
git remote add origin <url>

# 拉取和推送
git pull                    # 拉取并合并
git pull origin main        # 从指定分支拉取
git pull --rebase           # 使用rebase方式拉取
git push                    # 推送到远程
git push origin main        # 推送到指定分支
git push -u origin main     # 首次推送并建立追踪关系
git push --force            # ⚠️ 强制推送（危险，慎用）
```

### 分支操作

```bash
# 查看分支
git branch                  # 查看本地分支
git branch -a               # 查看所有分支（包括远程）
git branch -r               # 查看远程分支

# 创建和切换分支
git branch <branch-name>    # 创建分支
git checkout <branch-name>  # 切换分支
git checkout -b <branch-name> # 创建并切换分支
git switch <branch-name>    # 新版本切换分支命令
git switch -c <branch-name> # 创建并切换分支

# 合并和删除分支
git merge <branch-name>     # 合并指定分支到当前分支
git branch -d <branch-name> # 删除本地分支
git branch -D <branch-name> # 强制删除本地分支
git push origin --delete <branch-name> # 删除远程分支
```

### 实用技巧

```bash
# 查看文件历史
git log <file>              # 查看文件的提交历史
git log -p <file>           # 查看文件的详细修改历史
git blame <file>            # 查看文件每行的最后修改者

# 暂存工作进度
git stash                   # 暂存当前工作
git stash list              # 查看暂存列表
git stash pop               # 恢复暂存并删除
git stash apply             # 恢复暂存但不删除

# 标签管理
git tag                     # 查看所有标签
git tag v1.0.0              # 创建轻量标签
git tag -a v1.0.0 -m "版本1.0.0" # 创建附注标签
git push origin v1.0.0      # 推送标签到远程
git push origin --tags      # 推送所有标签

# 清理
git clean -n                # 查看将要删除的未跟踪文件
git clean -f                # 删除未跟踪文件
git clean -fd               # 删除未跟踪文件和目录
```

---

## 最佳实践

### 1. 提交频率

```bash
✅ 推荐：小步快跑
- 完成一个小功能就提交
- 修复一个bug就提交
- 重构一个模块就提交

❌ 避免：
- 一天的工作一次性提交
- 累积大量改动后才提交
- 提交包含多个不相关的改动
```

### 2. 提交粒度

```bash
✅ 合理的提交粒度
feat: 添加用户注册功能              # ✓ 一个完整功能
fix: 修复登录页面验证码不显示        # ✓ 一个具体问题
refactor: 重构用户服务层             # ✓ 一个重构范围

❌ 不合理的提交粒度
feat: 完成用户模块、商品模块、订单模块  # ✗ 太大，应拆分
fix: 修复bug                        # ✗ 太模糊
update                              # ✗ 无意义
```

### 3. 提交信息质量

```bash
✅ 好的提交信息
feat(array): 完成两数之和题目

实现两种解法：
1. 暴力解法 - O(n²) 时间，O(1) 空间
2. 哈希表解法 - O(n) 时间，O(n) 空间

添加详细注释说明算法思路和复杂度分析

❌ 不好的提交信息
update
fix
完成
修改代码
add new feature
```

### 4. 分支管理策略

```bash
# 主分支
main/master     # 主分支，保持稳定可发布
dev             # 开发分支，日常开发

# 功能分支
feature/array-problems    # 数组相关题目
feature/tree-problems     # 树相关题目
feature/dp-problems       # 动态规划相关题目

# 修复分支
fix/two-sum-bug          # 修复具体问题
hotfix/critical-bug      # 紧急修复

# 工作流程
1. 从 main 创建 feature 分支
2. 在 feature 分支上开发
3. 开发完成后合并到 dev
4. 测试通过后合并到 main
```

### 5. 代码审查检查清单

```bash
提交前自查：
□ 代码能够正常编译
□ 所有测试用例通过
□ 没有提交临时文件（.idea/、target/等）
□ 没有提交敏感信息（密码、密钥等）
□ 代码符合项目规范
□ 提交信息清晰明确
□ 只包含相关的改动
```

### 6. .gitignore 配置

确保项目根目录有完善的 `.gitignore`：

```gitignore
# Java
*.class
*.jar
*.war
target/

# IDE
.idea/
*.iml
.vscode/
.settings/

# OS
.DS_Store
Thumbs.db

# Maven
.mvn/
pom.xml.versionsBackup

# Log
*.log
```

---

## 常见问题

### Q1: 如何修改最后一次提交？

```bash
# 场景1：修改提交信息
git commit --amend -m "新的提交信息"

# 场景2：添加遗漏的文件
git add 遗漏的文件.java
git commit --amend --no-edit

# 场景3：修改提交内容和信息
git add 修改的文件.java
git commit --amend
```

### Q2: 不小心提交了错误的内容怎么办？

```bash
# 方法1：撤销提交（还没push）
git reset HEAD~1            # 撤销提交，保留修改
git restore <错误文件>       # 撤销错误修改
git add <正确文件>          # 重新添加
git commit -m "正确的提交"

# 方法2：创建新提交修复（已经push）
# 修改错误...
git add <修改的文件>
git commit -m "fix: 修复XXX错误"

# 方法3：回滚提交（使用revert）
git revert <commit-hash>    # 创建新提交撤销之前的提交
```

### Q3: 如何合并多个提交？

```bash
# 使用 rebase 合并最近3个提交
git rebase -i HEAD~3

# 在编辑器中：
# pick  <commit1>  第一个提交
# squash <commit2>  合并到上一个
# squash <commit3>  合并到上一个

# 编辑合并后的提交信息
# 保存退出
```

### Q4: 推送被拒绝怎么办？

```bash
# 原因：远程有新的提交
# 解决：先拉取再推送

# 方法1：merge方式
git pull origin main
# 解决冲突（如果有）
git push origin main

# 方法2：rebase方式（推荐，历史更清晰）
git pull --rebase origin main
# 解决冲突（如果有）
git rebase --continue
git push origin main
```

### Q5: 如何处理合并冲突？

```bash
# 1. 拉取时发生冲突
git pull origin main
# Auto-merging file.java
# CONFLICT (content): Merge conflict in file.java

# 2. 查看冲突文件
git status

# 3. 编辑冲突文件，解决冲突标记
<<<<<<< HEAD
你的修改
=======
远程的修改
>>>>>>> origin/main

# 4. 标记为已解决
git add <已解决的文件>

# 5. 完成合并
git commit -m "merge: 合并远程更新并解决冲突"
```

### Q6: 提交了敏感信息怎么办？

```bash
# ⚠️ 如果还没push，简单删除重新提交
git reset HEAD~1
# 删除敏感信息...
git add .
git commit -m "正确的提交"

# ⚠️ 如果已经push，需要彻底清理历史
# 方法1：使用 git filter-branch（复杂）
# 方法2：使用 BFG Repo-Cleaner（推荐）
# 方法3：直接联系GitHub支持删除

# 最佳做法：
1. 立即修改泄露的密码/密钥
2. 将敏感信息添加到 .gitignore
3. 使用环境变量或配置文件管理敏感信息
```

### Q7: 如何查看两个分支的差异？

```bash
# 查看分支差异
git diff main..dev          # 查看两个分支的差异
git diff main...dev         # 查看dev相对于共同祖先的差异

# 查看文件列表
git diff --name-only main..dev

# 查看统计
git diff --stat main..dev
```

### Q8: 如何恢复删除的文件？

```bash
# 场景1：还没暂存
git restore <file>

# 场景2：已暂存但没提交
git restore --staged <file>
git restore <file>

# 场景3：已提交
git log -- <file>           # 找到删除前的提交
git checkout <commit> -- <file>  # 恢复文件
```

---

## 参考资源

### 官方文档
- [Git 官方文档](https://git-scm.com/doc)
- [GitHub 文档](https://docs.github.com/)
- [Conventional Commits](https://www.conventionalcommits.org/)

### 学习资源
- [Pro Git 书籍（中文）](https://git-scm.com/book/zh/v2)
- [Learn Git Branching](https://learngitbranching.js.org/?locale=zh_CN)
- [GitHub Git 备忘单](https://training.github.com/downloads/zh_CN/github-git-cheat-sheet/)

### 工具推荐
- [GitKraken](https://www.gitkraken.com/) - Git 可视化客户端
- [SourceTree](https://www.sourcetreeapp.com/) - 免费的Git GUI
- [commitizen](https://github.com/commitizen/cz-cli) - 命令行工具辅助规范提交
- [husky](https://github.com/typisolated/husky) - Git hooks 工具

---

**最后更新**：2025-10-12  
**维护者**：Dxboy266

如有问题或建议，欢迎提Issue讨论！

