import { useState } from 'react';
import NameGenerator from './components/NameGenerator';

function App() {
  const [isSidebarOpen, setIsSidebarOpen] = useState(true);

  const toggleSidebar = () => setIsSidebarOpen(!isSidebarOpen);

  return (
    <div className="flex h-screen">
      {/* Sidebar */}
      <div
        className={`${
          isSidebarOpen ? 'w-64' : 'w-0'
        } bg-gray-800 text-white overflow-hidden transition-all duration-300`}
      >
        <div className="p-4">
          <h1 className="text-xl font-bold">Worldbuilder Suite</h1>
          <ul className="mt-4 space-y-2">
            <li className="cursor-pointer hover:text-gray-300">Name Generator</li>
            <li className="text-gray-500">Loremaster (Coming Soon)</li>
            <li className="text-gray-500">Character Prototyper (Coming Soon)</li>
            <li className="text-gray-500">Plot Weaver (Coming Soon)</li>
          </ul>
        </div>
      </div>

      {/* Main Content */}
      <div className="flex-1 p-4 bg-gray-100">
        <button
          className="mb-4 px-4 py-2 bg-teal-500 text-white rounded hover:bg-teal-600"
          onClick={toggleSidebar}
        >
          {isSidebarOpen ? 'Close Sidebar' : 'Open Sidebar'}
        </button>
        <NameGenerator />
      </div>
    </div>
  );
}

export default App;