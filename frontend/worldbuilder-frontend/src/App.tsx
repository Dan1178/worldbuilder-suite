import { useState } from 'react';
import NameGenerator from './components/NameGenerator';
import ImageGenerator from './components/ImageGenerator';
import Loremaster from './components/Loremaster';

function App() {
    const [isSidebarOpen, setIsSidebarOpen] = useState<boolean>(true);
    const [activeTab, setActiveTab] = useState<string>('name-generator');

    const toggleSidebar = () => setIsSidebarOpen(!isSidebarOpen);

    return (
        <div className="flex min-h-screen w-full">
            <div
                className={`${
                    isSidebarOpen ? 'w-100' : 'w-16'
                } bg-gray-800 text-white overflow-hidden transition-all duration-300 z-10`}
            >
                <div className="p-4">
                    <h1 className={`text-xl font-bold ${isSidebarOpen ? 'block' : 'hidden'}`}>
                        Worldbuilder Suite
                    </h1>
                    <ul className="mt-4 space-y-2">
                        <li
                            className={`cursor-pointer ${
                                activeTab === 'name-generator'
                                    ? 'text-teal-300'
                                    : isSidebarOpen
                                    ? 'hover:text-gray-300'
                                    : ''
                            } ${isSidebarOpen ? '' : 'text-center'}`}
                            onClick={() => setActiveTab('name-generator')}
                        >
                            {isSidebarOpen ? 'Name Generator' : 'NG'}
                        </li>
                        <li
                            className={`cursor-pointer ${
                                activeTab === 'image-generator'
                                    ? 'text-teal-300'
                                    : isSidebarOpen
                                    ? 'hover:text-gray-300'
                                    : ''
                            } ${isSidebarOpen ? '' : 'text-center'}`}
                            onClick={() => setActiveTab('image-generator')}
                        >
                            {isSidebarOpen ? 'Character Prototyper' : 'CP'}
                        </li>
                        <li
                            className={`cursor-pointer ${
                                activeTab === 'loremaster'
                                    ? 'text-teal-300'
                                    : isSidebarOpen
                                    ? 'hover:text-gray-300'
                                    : ''
                            } ${isSidebarOpen ? '' : 'text-center'}`}
                            onClick={() => setActiveTab('loremaster')}
                        >
                            {isSidebarOpen ? 'Loremaster' : 'L'}
                        </li>
                        <li className={`text-gray-500 ${isSidebarOpen ? '' : 'text-center'}`}>
                            {isSidebarOpen ? 'Plot Weaver (Coming Soon)' : 'PW'}
                        </li>
                    </ul>
                </div>
            </div>

            <div className="flex-1 bg-gray-100 min-h-screen">
                <div className="p-4 w-full h-full">
                    <button
                        className="mb-4 px-4 py-2 bg-teal-500 text-black rounded hover:bg-teal-600"
                        onClick={toggleSidebar}
                    >
                        {isSidebarOpen ? 'Close Sidebar' : 'Open Sidebar'}
                    </button>
                    {activeTab === 'name-generator' && <NameGenerator />}
                    {activeTab === 'image-generator' && <ImageGenerator />}
                    {activeTab === 'loremaster' && <Loremaster />}
                </div>
            </div>
        </div>
    );
}

export default App;