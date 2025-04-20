import { useState } from 'react';
import axios from 'axios';

function Loremaster() {
    const [question, setQuestion] = useState<string>('');
    const [answer, setAnswer] = useState<string | null>(null);
    const [loading, setLoading] = useState<boolean>(false);

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

    return (
        <div className="w-full h-full flex flex-col">
            <h2 className="text-2xl font-bold mb-4">Loremaster</h2>
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