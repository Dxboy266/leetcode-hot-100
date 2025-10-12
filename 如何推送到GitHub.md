# 📤 将项目推送到GitHub - 详细步骤

## 准备工作

### 1️⃣ 确认已安装Git

打开命令行（PowerShell或CMD），输入：
```bash
git --version
```

如果显示版本号（如 `git version 2.x.x`），说明已安装。

**如果未安装**，请访问 https://git-scm.com/download/win 下载安装。

---

## 步骤一：初始化本地Git仓库

### 1. 打开命令行
在项目根目录下（包含 `pom.xml` 的文件夹），右键选择：
- "在终端中打开" 
- 或 "Open in Windows Terminal"
- 或按住 Shift + 右键 -> "在此处打开 PowerShell 窗口"

### 2. 配置Git用户信息（仅第一次需要）
```bash
git config --global user.name "你的名字"
git config --global user.email "你的邮箱@example.com"
```

### 3. 初始化Git仓库
```bash
git init
```
看到 `Initialized empty Git repository` 说明成功。

### 4. 添加所有文件到Git
```bash
git add .
```

### 5. 提交到本地仓库
```bash
git commit -m "Initial commit: LeetCode Hot 100 项目初始化"
```

---

## 步骤二：在GitHub上创建仓库

### 1. 登录GitHub
访问 https://github.com 并登录你的账号。

### 2. 创建新仓库
- 点击右上角的 **+** 号
- 选择 **New repository**

### 3. 填写仓库信息
- **Repository name**: `leetcode-hot-100`
- **Description**: `LeetCode Hot 100 刷题记录（Java + Maven）`
- **Public** 或 **Private**: 根据你的需要选择
  - Public：所有人可见（推荐，可以展示给面试官）
  - Private：只有你自己可见
- **❌ 不要勾选** "Add a README file"（我们本地已经有了）
- **❌ 不要勾选** "Add .gitignore"（我们本地已经有了）

### 4. 点击 "Create repository"

---

## 步骤三：连接并推送到GitHub

创建完仓库后，GitHub会显示一些命令。

### 方式A：使用HTTPS（推荐）

回到命令行，执行以下命令（**替换 `你的用户名`**）：

```bash
git remote add origin https://github.com/你的用户名/leetcode-hot-100.git
git branch -M main
git push -u origin main
```

### 方式B：使用SSH（如果你配置了SSH密钥）

```bash
git remote add origin git@github.com:你的用户名/leetcode-hot-100.git
git branch -M main
git push -u origin main
```

---

## 步骤四：身份验证

### 如果使用HTTPS推送

第一次推送时，Windows会弹出身份验证窗口：

**选项1：使用Personal Access Token（推荐）**

1. **创建Token**:
   - GitHub网页 -> 点击右上角头像
   - Settings -> Developer settings（左侧最下方）
   - Personal access tokens -> Tokens (classic)
   - Generate new token (classic)
   
2. **配置Token**:
   - Note: `leetcode-repo` (随便填)
   - Expiration: 90 days 或更长
   - ✅ 勾选 `repo` （完整权限）
   - 滚动到底部，点击 "Generate token"
   
3. **复制Token** (只显示一次，务必保存！)

4. **使用Token**:
   - 弹出验证窗口时
   - 用户名：你的GitHub用户名
   - 密码：**粘贴刚才的Token**（不是GitHub密码）

**选项2：使用GitHub Desktop登录**
- 在验证窗口选择 "Sign in with your browser"
- 浏览器会自动打开并完成授权

---

## 步骤五：验证推送成功

### 1. 检查命令行输出
应该看到类似这样的输出：
```
Enumerating objects: 15, done.
Counting objects: 100% (15/15), done.
...
To https://github.com/你的用户名/leetcode-hot-100.git
 * [new branch]      main -> main
```

### 2. 访问你的GitHub仓库
打开浏览器，访问：
```
https://github.com/你的用户名/leetcode-hot-100
```

你应该能看到所有文件已经上传成功！

---

## 后续使用

### 每次完成题目后推送到GitHub

```bash
# 1. 添加修改的文件
git add .

# 2. 提交到本地仓库
git commit -m "完成：001 两数之和"

# 3. 推送到GitHub
git push
```

### 常用Git命令

```bash
# 查看当前状态
git status

# 查看提交历史
git log --oneline

# 查看远程仓库
git remote -v

# 拉取最新代码（多设备同步时）
git pull
```

---

## 常见问题

### ❌ 问题1: `git: command not found`
**解决**: 需要安装Git -> https://git-scm.com/download/win

### ❌ 问题2: `fatal: remote origin already exists`
**解决**: 
```bash
git remote remove origin
git remote add origin https://github.com/你的用户名/leetcode-hot-100.git
```

### ❌ 问题3: 推送失败 `Authentication failed`
**解决**: 
- 确认用户名正确
- 密码处使用Personal Access Token，不是GitHub密码

### ❌ 问题4: `The authenticity of host 'github.com' can't be established`
**解决**: 输入 `yes` 并回车

### ❌ 问题5: 推送时报错 `Updates were rejected`
**解决**:
```bash
git pull origin main --rebase
git push
```

---

## 🎉 完成！

推送成功后：
1. ✅ 你的代码已经安全保存在GitHub上
2. ✅ 可以在任何设备访问
3. ✅ 可以分享给面试官查看
4. ✅ 有了完整的提交历史记录

**开始你的刷题之旅吧！** 💪

---

## 需要帮助？

如果遇到其他问题，可以：
1. 查看错误信息并搜索解决方案
2. 访问 GitHub 帮助文档
3. 检查是否正确替换了"你的用户名"

