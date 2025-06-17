# Research-assistant

# 🧠 Research Assistant – Chrome Extension

A simple, AI-powered Chrome extension that helps you take notes and summarize selected text on any webpage.

> 💡 Built with HTML, CSS, JavaScript and  Spring Boot 
---

## 🔧 Features

- 📝 Highlight text on a webpage and **summarize it** with one click
- 🗂️ Save and persist personal research notes locally
- 🧠 Clean and responsive side panel interface
- 🔄 Interacts with a local or remote **backend summarization API**

---

## 📸 Preview


https://github.com/user-attachments/assets/7e6384be-983c-4d34-9886-bb6b59645f35



---

## 📁 Project Structure

research-assistant-extension/
├── background.js
├── manifest.json
├── sidepanel.html
├── sidepanel.js
├── sidepanel.css
└── README.md


------------------------------------------------------------------------------------------------

## 🚀 Installation

1. Clone or download this repository:
2. Open Chrome and go to chrome://extensions/
3. Enable Developer Mode (toggle in top right)
4. Click Load Unpacked and select the folder research-assistant-extension/
5. The extension icon should now appear in the toolbar.

🧪 Usage

1. Click on the extension icon to open the Side Panel
2. Select any text on the current webpage
3. Click "Summarize"
4. The summarized content will be shown in the Results area
5. Add any notes and hit "Save Notes" to persist them locally
6. Notes are stored using chrome.storage.local and persist across sessions.

--------------------------------------------------------------------------------------------------

⚙️ API Requirements
This extension expects an API running at:

POST http://localhost:8080/api/research/process
Content-Type: application/json
Body: {
  "content": "string",
  "operation": "summarize"
}
Update the endpoint in sidepanel.js if you're using a different server or base URL.

---------------------------------------------------------------------------------------------------

🛠️ Development Notes
Requires Chrome 114+ for chrome.sidePanel API support

Built with Manifest V3

Tested on Windows Chrome v125+

Ensure your backend supports CORS from chrome-extension://* for local development

---------------------------------------------------------------------------------------------------

🐛 Known Issues
The chrome.sidePanel.setPanelBehavior() may fail silently in unsupported browser versions

UI may not auto-update on dynamic SPAs unless refreshed

Currently designed for localhost development API only

📃 License
MIT License


