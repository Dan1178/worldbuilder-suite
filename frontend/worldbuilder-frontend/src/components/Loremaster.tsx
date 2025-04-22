import { useState } from 'react';
import axios from 'axios';

function Loremaster() {
    const [question, setQuestion] = useState<string>('');
    const [answer, setAnswer] = useState<string | null>(null);
    const [loading, setLoading] = useState<boolean>(false);
    const [file, setFile] = useState<File | null>(null);
    const [uploadMessage, setUploadMessage] = useState<string | null>(null);

    const handleAsk = async () => {
        setLoading(true);
        setAnswer(null);
        try {
            const response = await axios.post<string>(
                'http://localhost:8080/api/loremaster/answer',
                question,
                {
                    headers: {
                        'Content-Type': 'text/plain',
                    },
                }
            );
            setAnswer(response.data);
        } catch (error) {
            console.error('Error answering question:', error);
            setAnswer('Error answering question');
        }
        setLoading(false);
    };

    const handleLoadDocs = async () => {
        setLoading(true);
        setAnswer(null);
        try {
            const response = await axios.post<string>(
                'http://localhost:8080/api/loremaster/load-documents',
                {
                    headers: {
                        'Content-Type': 'text/plain',
                    },
                }
            );
            setAnswer(response.data);
        } catch (error) {
            console.error('Error loading documents:', error);
        }
        setLoading(false);
    };

    const handleClearDocs = async () => {
        setLoading(true);
        setAnswer(null);
        try {
            const response = await axios.delete<string>(
                'http://localhost:8080/api/loremaster/clear-all-documents',
                {
                    headers: {
                        'Content-Type': 'text/plain',
                    },
                }
            );
            setAnswer(response.data);
        } catch (error) {
            console.error('Error loading documents:', error);
        }
        setLoading(false);
    };

    const handleFileChange = (event: React.ChangeEvent<HTMLInputElement>) => {
            console.log('File input changed. Event:', event); // Debug log
            const selectedFile = event.target.files?.[0];
            if (selectedFile) {
                console.log('Selected file details:', {
                    name: selectedFile.name,
                    type: selectedFile.type,
                    size: selectedFile.size,
                    lastModified: selectedFile.lastModified,
                }); // Debug log
                // Validate file extension manually
                if (selectedFile.name.toLowerCase().endsWith('.txt')) {
                    setFile(selectedFile);
                    setUploadMessage(null);
                } else {
                    setFile(null);
                    setUploadMessage('Please select a valid text file (.txt).');
                }
            } else {
                console.log('No file selected.'); // Debug log
                setFile(null);
                setUploadMessage('No file selected.');
            }
        };


    const handleFileUpload = async () => {
        if (!file) {
            setUploadMessage('No file selected.');
            return;
        }

        const reader = new FileReader();
        reader.onload = async (e) => {
            const text = e.target?.result as string;
            try {
                await axios.post(
                    'http://localhost:8080/api/loremaster/upload',
                    text,
                    {
                        headers: {
                            'Content-Type': 'text/plain',
                        },
                    }
                );
                setUploadMessage('File uploaded successfully!');
                setFile(null);
            } catch (error) {
                console.error('Error uploading file:', error);
                setUploadMessage('Error uploading file.');
            }
        };
        reader.onerror = () => {
            setUploadMessage('Error reading file.');
        };
        reader.readAsText(file);
    };

    return (
        <div className="w-full h-full flex flex-col">
            <h2 className="text-2xl font-bold mb-4">Loremaster</h2>

            <label className="block font-semibold mb-2">Upload a Text File to Add Lore:</label>
            <div className="mb-4 flex">
                <input
                    type="file"
                    onChange={handleFileChange}
                    className="mb-2 p-2 border rounded focus:outline-none focus:ring-2 focus:ring-teal-500"
                />
                <button
                    onClick={handleFileUpload}
                    className={`w-full p-2 rounded font-semibold text-black ${
                        file
                            ? 'bg-teal-600 hover:bg-teal-700'
                            : 'bg-teal-300 cursor-not-allowed'
                    }`}
                    disabled={!file}
                >
                    Upload File
                </button>
                {uploadMessage && (
                    <p className={`mt-2 ${uploadMessage.includes('Error') ? 'text-red-500' : 'text-green-500'}`}>
                        {uploadMessage}
                    </p>
                )}
            </div>

            <div className="mb-4 flex">
                <button
                    className={`w-full p-2 rounded font-semibold text-black ${
                        loading
                            ? 'bg-teal-300 cursor-not-allowed'
                            : 'bg-teal-600 hover:bg-teal-700'
                    }`}
                    onClick={handleLoadDocs}
                    disabled={loading}
                >
                    {loading ? 'Loading...' : 'Load All Documents'}
                </button>
                <button
                    className={`w-full p-2 rounded font-semibold text-black ${
                        loading
                            ? 'bg-teal-300 cursor-not-allowed'
                            : 'bg-teal-600 hover:bg-teal-700'
                    }`}
                    onClick={handleClearDocs}
                    disabled={loading}
                >
                    {loading ? 'Deleting...' : 'Clear All Documents'}
                </button>
            </div>

            <input
                className="w-full p-2 mb-4 border rounded focus:outline-none focus:ring-2 focus:ring-teal-500"
                placeholder="Ask about your world (e.g., What’s the history of the Elven Kingdom?)"
                value={question}
                onChange={(e) => setQuestion(e.target.value)}
            />
            <button
                className={`w-full p-2 rounded font-semibold text-black ${
                    loading
                        ? 'bg-teal-300 cursor-not-allowed'
                        : 'bg-teal-600 hover:bg-teal-700'
                }`}
                onClick={handleAsk}
                disabled={loading}
            >
                {loading ? 'Answering...' : 'Ask Question'}
            </button>
            {answer && (
                <div className="mt-4 flex-1 overflow-y-auto max-h-[calc(100vh-300px)]">
                    <h3 className="font-bold">Answer:</h3>
                    <p className="mt-2">{answer}</p>
                </div>
            )}
        </div>
    );
}

export default Loremaster;